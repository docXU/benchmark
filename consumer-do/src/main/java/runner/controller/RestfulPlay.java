package runner.controller;

import bigbang.i.IBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Matt Xu on 2018/3/26
 */

@RestController
public class RestfulPlay {
    @Resource
    private IBusinessService iBusinessService;

    @RequestMapping(value = "/businesses/{bid}", method = RequestMethod.GET)
    public String getUser(@PathVariable String bid) {
        return iBusinessService.qurey(bid).toString();
    }
}
