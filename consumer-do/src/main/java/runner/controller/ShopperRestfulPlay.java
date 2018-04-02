package runner.controller;

import bigbang.e.ErrorBody;
import bigbang.i.IBusinessService;
import bigbang.i.IShopperService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import provider.domain.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/28
 */

@RestController
@RequestMapping(value = "/shoppers")
public class ShopperRestfulPlay {
    @Resource
    private IShopperService shopperService;
    @Resource
    private IBusinessService businessService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.ShopperView.class)
    public Shopper saveBusiness(@RequestBody Shopper shopper) {
        return (Shopper) shopperService.create(shopper);
    }

    @RequestMapping(value = "/{sid}")
    @JsonView(View.ShopperView.class)
    public Shopper getShopper(@PathVariable String sid) {
        return (Shopper) shopperService.query(sid);
    }

    @RequestMapping(value = "/{sid}/businesses", method = RequestMethod.GET)
    @JsonView(View.BusinessView.class)
    public Set<Business> queryBusinesses(@PathVariable String sid) {
        return shopperService.queryBusinesses(sid);
    }

    @RequestMapping(value = "/{sid}/businesses", method = RequestMethod.POST)
    public String beVIP(@PathVariable String sid, @RequestParam String bid) {
        try {
            businessService.addVIP(bid, (Shopper) shopperService.query(sid));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorBody().setCode(500).setMessage("操作失败，请检查参数后重试").toString();
        }
    }

    @RequestMapping(value = "/{sid}/businesses/{bid}", method = RequestMethod.GET)
    public boolean verifyBusinesses(@PathVariable String sid, @PathVariable String bid) {
        return shopperService.verifyVIP(sid, bid);
    }

    @RequestMapping(value = "/{sid}/businesses/{bid}", method = RequestMethod.DELETE)
    public String deleteBusinesses(@PathVariable String sid, @PathVariable String bid) {
        try {
            businessService.deleteVIP(bid, sid);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorBody().setCode(500).setMessage("操作失败，请检查参数后重试").toString();
        }
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public String placeOrder(@RequestBody Order order) {
        try {
            //TODO: 疯转一个ID生成类，创建一个全局唯一ID
            order.setOid(5);
            order.setCreate_time(new Date(System.currentTimeMillis()));
            order.setLast_update_time((int) System.currentTimeMillis());
            order.setCid(null);
            return shopperService.placeOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorBody().setCode(500).setMessage("操作失败，请检查参数后重试").toString();
        }
    }

    @RequestMapping(value = "/{sid}/orders", method = RequestMethod.GET)
    public List<Order> getOrdersWithShopper(@PathVariable int sid) {
        try {
            return shopperService.getMyAllOrders(sid);
        } catch (Exception e) {
            e.printStackTrace();
            //return new ErrorBody().setCode(500).setMessage("操作失败，请检查参数后重试").toString();
        }
        return null;
    }

    @RequestMapping(value = "/{sid}/coupons", method = RequestMethod.GET)
    public List<Coupon> getMyCoupons(@PathVariable int sid) {
        try {
            return shopperService.getMyCoupons(sid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{sid}/orders/businesses/{bid}", method = RequestMethod.GET)
    public List<Order> getOrdersWithShopperInBusiness(@PathVariable int sid, @PathVariable int bid) {
        try {
            return shopperService.getMyOrdersWithBusiness(sid, bid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
