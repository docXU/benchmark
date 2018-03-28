package runner.controller;

import bigbang.i.IBusinessService;
import bigbang.i.IShopperService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import provider.domain.Business;
import provider.domain.Shopper;
import provider.domain.View;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

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
    public String getBusiness(@PathVariable String bid) {
        return ((Business) businessService.qurey(bid)).toString();
    }

    @RequestMapping(value = "/{bid}", method = RequestMethod.PUT, consumes = "application/json")
    public Business updateBusiness(@RequestBody Business business) {
        return (Business) businessService.update(business);
    }

    @RequestMapping(value = "/{bid}/shoppers", method = RequestMethod.GET)
    @JsonView(View.VIPView.class)
    public Set queryVIP(@PathVariable String bid) {
        return businessService.queryAllVIP(bid);
    }

    @RequestMapping(value = "/{bid}/shoppers", method = RequestMethod.POST)
    @JsonView(View.VIPView.class)
    public Shopper addVIP(@PathVariable String bid, @RequestParam String sid) {
        return (Shopper) businessService.addVIP(bid, (Shopper) shopperService.qurey(sid));
    }

    @RequestMapping(value = "/{bid}/shoppers/{sid}", method = RequestMethod.GET)
    @JsonView(View.VIPView.class)
    public Shopper virifyVIP(@PathVariable String bid, @PathVariable String sid) {
        return (Shopper) businessService.queryVIPDetail(bid, sid);
    }

    @RequestMapping(value = "/{bid}/shoppers/{sid}", method = RequestMethod.DELETE)
    public String deleteVIP(@PathVariable String bid, @PathVariable String sid) {
        return businessService.deleteVIP(bid, sid);
    }

}
