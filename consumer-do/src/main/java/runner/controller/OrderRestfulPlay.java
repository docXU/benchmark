package runner.controller;

import bigbang.e.ErrorBody;
import bigbang.i.IOrderService;
import org.springframework.web.bind.annotation.*;
import provider.domain.Order;

import javax.annotation.Resource;

/**
 * Created by Matt Xu on 2018/3/29
 * @author xuxiongwei
 */

@RestController
@RequestMapping(value = "/orders")
public class OrderRestfulPlay {
    @Resource
    IOrderService orderService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    String placeOrder(@RequestBody Order order) {
        try {
            return orderService.placeOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorBody().setCode(500).setMessage("操作失败，请检查参数后重试").toString();
        }
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
