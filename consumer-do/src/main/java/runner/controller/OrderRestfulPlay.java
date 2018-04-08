package runner.controller;

import bigbang.i.IOrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provider.domain.Order;
import runner.util.RedisHandler;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Matt Xu on 2018/3/29
 * @author xuxiongwei
 */

@RestController
@RequestMapping(value = "/orders")
public class OrderRestfulPlay {
    @Resource
    private IOrderService<Order> orderService;

    @Resource
    private RedisTemplate<String, List<Order>> redisTemplate;

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity placeOrder(@RequestBody Order order) {
        try {
            order.setCreate_time(new Date());
            Order res = orderService.placeOrder(order);
            RedisHandler.refreshRedisCache(redisTemplate, res);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //return new ErrorBody().setCode(500).setMessage("操作失败，请检查参数后重试").toString();
            return new ResponseEntity("操作失败，请检查参数后重试", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    Order updateOrder(@PathVariable Order order) {
        Order res = orderService.update(order);
        RedisHandler.refreshRedisCache(redisTemplate, res);
        return res;
    }

    @RequestMapping(value = "/{oid}/status", method = RequestMethod.GET)
    String getStatus(@PathVariable int oid) {
        return String.valueOf(orderService.getStatus(oid));
    }
}
