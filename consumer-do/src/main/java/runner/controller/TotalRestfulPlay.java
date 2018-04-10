package runner.controller;

import bigbang.i.IOrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import provider.domain.Order;
import provider.domain.TotalBody;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Matt Xu on 2018/4/4
 *
 * @author xuxiongwei
 */

@RestController
@RequestMapping(value = "/total")
@Transactional(rollbackOn = Exception.class)
public class TotalRestfulPlay {
    @Resource
    private IOrderService<provider.domain.Order> orderService;

    @Resource
    private RedisTemplate<String, List<Order>> redisTemplate;

    @RequestMapping(value = "/shoppers/{sid}/years/{year}", method = RequestMethod.GET)
    TotalBody getShopperTotalInYear(@PathVariable int sid, @PathVariable("year") String year) {
        return new TotalBody(sid, TotalBody.TotalType.YEAR, year,
                Objects.requireNonNull(getAndCacheOrderInYear("Shopper", sid, year)));
    }

    @RequestMapping(value = "/shoppers/{sid}/years/{year}/mouths/{mouth}", method = RequestMethod.GET)
    TotalBody getShopperTotalInMouth(@PathVariable int sid,
                                     @PathVariable("year") String year,
                                     @PathVariable("mouth") String mouth) {
        List<Order> yearList = getAndCacheOrderInYear("Shopper", sid, year);
        try {
            //过滤出指定月的数据(getMonth 有坑)
            List<Order> data = yearList.stream()
                    .filter(order -> order.getCreate_time().getMonth() + 1 == Integer.parseInt(mouth))
                    .collect(Collectors.toList());
            return new TotalBody(sid, TotalBody.TotalType.MOUTH, year + "-" + mouth, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/shoppers/{sid}/years/{year}/mouths/{mouth}/days/{day}", method = RequestMethod.GET)
    TotalBody getShopperTotalInDay(@PathVariable int sid,
                                   @PathVariable("year") String year,
                                   @PathVariable("mouth") String mouth,
                                   @PathVariable("day") String day) {
        List<Order> yearList = getAndCacheOrderInYear("Shopper", sid, year);
        //过滤出指定日的数据
        List<Order> data = yearList.stream()
                .filter(order -> order.getCreate_time().getMonth() + 1 == Integer.parseInt(mouth)
                        && order.getCreate_time().getDate() == Integer.parseInt(day))
                .collect(Collectors.toList());
        return new TotalBody(sid, TotalBody.TotalType.DAY, year + "-" + mouth + "-" + day, data);
    }

    @RequestMapping(value = "/businesses/{bid}/years/{year}", method = RequestMethod.GET)
    TotalBody getBusinessTotalInYear(@PathVariable int bid,
                                     @PathVariable("year") String year) {
        return new TotalBody(bid, TotalBody.TotalType.YEAR, year, Objects.requireNonNull(getAndCacheOrderInYear("Businesses", bid, year)));
    }

    @RequestMapping(value = "/businesses/{bid}/years/{year}/mouths/{mouth}", method = RequestMethod.GET)
    TotalBody getBusinessTotalInMouth(@PathVariable int bid,
                                      @PathVariable("year") String year,
                                      @PathVariable("mouth") String mouth) {
        List<Order> yearList = getAndCacheOrderInYear("Business", bid, year);
        try {
            //过滤出指定月的数据(getMonth 有坑 0-base)
            List<Order> data = yearList.stream()
                    .filter(order -> Integer.parseInt(mouth) == order.getCreate_time().getMonth() + 1)
                    .collect(Collectors.toList());
            return new TotalBody(bid, TotalBody.TotalType.MOUTH, year + "-" + mouth, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/businesses/{bid}/years/{year}/mouths/{mouth}/days/{day}", method = RequestMethod.GET)
    TotalBody getBusinessTotalInDay(@PathVariable int bid,
                                    @PathVariable("year") String year,
                                    @PathVariable("mouth") String mouth,
                                    @PathVariable("day") String day) {
        List<Order> yearList = getAndCacheOrderInYear("Business", bid, year);

        try {
            //过滤出指定日的数据
            List<Order> data = yearList.stream()
                    .filter(order -> order.getCreate_time().getMonth() + 1 == Integer.parseInt(mouth)
                            && order.getCreate_time().getDate() == Integer.parseInt(day))
                    .collect(Collectors.toList());
            return new TotalBody(bid, TotalBody.TotalType.DAY, year + "-" + mouth + "-" + day, data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Order> getAndCacheOrderInYear(String who, int id, String year) {
        List<Order> yearList = redisTemplate.opsForValue().get("get" + who + "TotalInYear" + id + "year" + year);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (yearList == null) {
            try {
                if ("Shopper".equals(who)) {
                    yearList = orderService.getOrdersBySidBetweenTime(id,
                            sd.parse(year + "-01-01 00:00:00"),
                            sd.parse(year + "-12-31 23:59:59"));
                } else {
                    yearList = orderService.getOrdersByBidBetweenTime(id,
                            sd.parse(year + "-01-01 00:00:00"),
                            sd.parse(year + "-12-31 23:59:59"));
                }
                //设置1天后过期的缓存
                redisTemplate.opsForValue().set(who + "TotalInYear" + id + "year" + year,
                        yearList, 1, TimeUnit.HOURS);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return yearList;
    }
}
