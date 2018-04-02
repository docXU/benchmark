package runner.controller;

import bigbang.i.IBusinessService;
import bigbang.i.IShopperService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import provider.domain.Business;
import provider.domain.Coupon;
import provider.domain.Shopper;
import provider.domain.View;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Matt Xu on 2018/3/26
 */

@RestController
@RequestMapping(value = "/businesses")
public class BusinessRestfulPlay {
    @Resource
    private IBusinessService businessService;
    @Resource
    private IShopperService shopperService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Business saveBusiness(@RequestBody Business business) {
        business.setCreate_time(new Date(System.currentTimeMillis()));
        return (Business) businessService.create(business);
    }

    @RequestMapping(value = "/{bid}", method = RequestMethod.GET, consumes = "application/json")
    @JsonView(View.BusinessView.class)
    public Business getBusiness(@PathVariable String bid) {
        return ((Business) businessService.query(bid));
    }

    @RequestMapping(value = "/{bid}", method = RequestMethod.PUT, consumes = "application/json")
    public Business updateBusiness(@RequestBody Business business) {
        return (Business) businessService.update(business);
    }

    @RequestMapping(value = "/{bid}/coupons", method = RequestMethod.POST)
    public Coupon createCoupon(@PathVariable int bid, @RequestBody Coupon coupon) {
        //TODO:同样的，设计一套ID生成类
        //https://www.cnblogs.com/haoxinyue/p/5208136.html
        //coupon.setCid(***);
        coupon.setBid(bid);
        try {
            return (Coupon) businessService.createCoupon(coupon);
        } catch (Exception e) {
            //cid重复之类的异常
            return null;
        }
    }

    @RequestMapping(value = "/{bid}/coupons", method = RequestMethod.GET)
    public List<Coupon> getAllCoupons(@PathVariable String bid) {
        return businessService.getMyCoupons(Integer.parseInt(bid));
    }

    @RequestMapping(value = "/{bid}/shoppers", method = RequestMethod.GET)
    @JsonView(View.ShopperView.class)
    public Set queryVIP(@PathVariable String bid) {
        return businessService.queryAllVIP(bid);
    }

    @RequestMapping(value = "/{bid}/shoppers", method = RequestMethod.POST)
    @JsonView(View.ShopperView.class)
    public Shopper addVIP(@PathVariable String bid, @RequestParam String sid) {
        try {
            return (Shopper) businessService.addVIP(bid, (Shopper) shopperService.query(sid));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/{bid}/shoppers/{sid}", method = RequestMethod.GET)
    @JsonView(View.ShopperView.class)
    public Shopper verifyVIP(@PathVariable String bid, @PathVariable String sid) {
        return (Shopper) businessService.queryVIPDetail(bid, sid);
    }

    @RequestMapping(value = "/{bid}/shoppers/{sid}", method = RequestMethod.DELETE)
    public String deleteVIP(@PathVariable String bid, @PathVariable String sid) {
        return businessService.deleteVIP(bid, sid);
    }

}
