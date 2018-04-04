package runner.controller;

import bigbang.e.ErrorBody;
import bigbang.i.IOrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import provider.domain.TotalBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * Created by Matt Xu on 2018/4/4
 *
 * @author xuxiongwei
 */

@RestController
@RequestMapping(value = "/total")
public class TotalRestfulPlay {
    @Resource
    private IOrderService orderService;

    @Resource
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/shoppers/{sid}/years/{year}", method = RequestMethod.GET)
    TotalBody getShopperTotalInYear(@PathVariable int sid, @PathVariable("year") String year) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new TotalBody(sid, TotalBody.TotalType.YEAR, year, orderService.getOrdersBySidBetweenTime(sid,
                    sd.parse(new StringBuilder().append(year).append("-01-01 00:00:00").toString()),
                    sd.parse(new StringBuilder().append(year).append("-12-31 23:59:59").toString()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/shoppers/{sid}/years/{year}/mouths/{mouth}", method = RequestMethod.GET)
    TotalBody getShopperTotalInMouth(@PathVariable int sid, @PathVariable("year") String year, @PathVariable("mouth") String mouth) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new TotalBody(sid, TotalBody.TotalType.MOUTH, year + "-" + mouth, orderService.getOrdersBySidBetweenTime(sid,
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-01 00:00:00").toString()),
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-31 23:59:59").toString()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/shoppers/{sid}/years/{year}/mouths/{mouth}/days/{day}", method = RequestMethod.GET)
    TotalBody getShopperTotalInDay(@PathVariable int sid, @PathVariable("year") String year, @PathVariable("mouth") String mouth, @PathVariable("day") String day) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new TotalBody(sid, TotalBody.TotalType.DAY, year + "-" + mouth + "-" + day, orderService.getOrdersBySidBetweenTime(sid,
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-").append(day).append(" 00:00:00").toString()),
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-").append(day).append(" 23:59:59").toString()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/Businesses/{bid}/years/{year}", method = RequestMethod.GET)
    TotalBody getBusinessTotalInYear(@PathVariable int bid, @PathVariable("year") String year) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new TotalBody(bid, TotalBody.TotalType.YEAR, year, orderService.getOrdersByBidBetweenTime(bid,
                    sd.parse(new StringBuilder().append(year).append("-01-01 00:00:00").toString()),
                    sd.parse(new StringBuilder().append(year).append("-12-31 23:59:59").toString()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/Businesses/{bid}/years/{year}/mouths/{mouth}", method = RequestMethod.GET)
    TotalBody getBusinessTotalInMouth(@PathVariable int bid, @PathVariable("year") String year, @PathVariable("mouth") String mouth) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new TotalBody(bid, TotalBody.TotalType.MOUTH, year + "-" + mouth, orderService.getOrdersByBidBetweenTime(bid,
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-01 00:00:00").toString()),
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-31 23:59:59").toString()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/Businesses/{bid}/years/{year}/mouths/{mouth}/days/{day}", method = RequestMethod.GET)
    TotalBody getBusinessTotalInDay(@PathVariable int bid, @PathVariable("year") String year, @PathVariable("mouth") String mouth, @PathVariable("day") String day) {
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new TotalBody(bid, TotalBody.TotalType.DAY, year + "-" + mouth + "-" + day, orderService.getOrdersByBidBetweenTime(bid,
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-").append(day).append(" 00:00:00").toString()),
                    sd.parse(new StringBuilder().append(year).append("-").append(mouth).append("-").append(day).append(" 23:59:59").toString()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
