package com.example.web;

import com.example.domain.Shopper;
import com.example.service.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shoppers")
public class ShopperController {
    @Autowired
    private IShopperService shopperService;

    @RequestMapping(value = "/{sid}")
    public Shopper getShopper(@PathVariable int sid)
    {
        return shopperService.findOne(sid);
    }

    @RequestMapping(value = "/page/{offset}/{limit}")
    public Page<Shopper> findAllByPage(@PathVariable int offset, @PathVariable int limit)
    {
        Sort sort = new Sort(Sort.Direction.ASC,"sid"); //创建sid降序排序
        Pageable p = new PageRequest(offset, limit, sort);
        return shopperService.findAllByPage(p);
    }

}
