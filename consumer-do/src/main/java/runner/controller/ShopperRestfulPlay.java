package runner.controller;

import bigbang.i.IBusinessService;
import bigbang.i.IShopperService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import provider.domain.Business;
import provider.domain.Shopper;
import provider.domain.View;

import javax.annotation.Resource;
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
        }
        return "failure";
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
        }
        return "failure";
    }
}