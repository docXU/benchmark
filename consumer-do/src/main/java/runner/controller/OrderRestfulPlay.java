package runner.controller;

import bigbang.i.IOrderService;
import org.springframework.web.bind.annotation.*;
import provider.domain.Order;

import javax.annotation.Resource;

/**
 * Created by Matt Xu on 2018/3/29
 */

@RestController
@RequestMapping("/orders")
public class OrderRestfulPlay {
    @Resource
    IOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    String placeOrder(@RequestBody Order order) {
        try {
            return orderService.placeOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failure";
    }

    @RequestMapping(value = "/{oid}/status", method = RequestMethod.GET)
    String getStatus(@PathVariable int oid) {
        return String.valueOf(orderService.getStatus(oid));
    }

    @RequestMapping(value = "/{oid}", method = RequestMethod.PUT)
    String updateOrder(@PathVariable Order order) {
        return String.valueOf(orderService.update(order));
    }

}
