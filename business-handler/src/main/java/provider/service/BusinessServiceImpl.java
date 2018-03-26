package provider.service;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;
import bigbang.i.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provider.domain.Business;
import provider.domain.Shopper;
import provider.domain.VIP;
import provider.repository.BusinessJpaRepository;

import java.util.*;


@Service
@Transactional
public class BusinessServiceImpl implements IBusinessService<Business> {

    @Autowired
    BusinessJpaRepository businessJpaRepository;

    @Override
    public Shopper addVIP(String bid, AbstractShopper shopper) {
        Business bus = businessJpaRepository.findById(Integer.parseInt(bid)).get();
        bus.getShoppers().add((Shopper) shopper);
        bus.setCreate_time(new Date());
        businessJpaRepository.save(bus);
        return (Shopper) shopper;
    }

    @Override
    public Set<? extends AbstractShopper> queryAllVIP(String bid) {
        Business business;
        try {
            business = businessJpaRepository.findById(Integer.parseInt(bid)).get();
        } catch (Exception e) {
            throw new NullPointerException("不存在商家");
        }
        return business.getShoppers();
    }

    @Override
    public VIP queryVIPDetail(String bid, String sid) {
        Set<? extends AbstractShopper> shoppers = queryAllVIP(bid);
        VIP vip;
        try {
            vip = (VIP) shoppers.stream().filter(shopper -> Integer.parseInt(sid) == ((Shopper) shopper).getSid()).findFirst().get();
            //TODO:优惠券查询
            vip.setCoupons(null);
            return vip;
        } catch (Exception e) {
            throw new NullPointerException("不存的顾客id");
        }
    }

    @Override
    public String deleteVIP(String bid, String sid) {
        return null;
    }

    @Override
    public AbstractCoupon createCoupon(AbstractCoupon params) {
        return null;
    }

    @Override
    public String deliverCoupon(String bid, String sid) {
        return null;
    }

    @Override
    public int getBusinessMaxId() {
        return businessJpaRepository.getBusinessMaxId();
    }

    @Override
    public Business create(Business obj) {
        if (0 == obj.getBid()) {
            if (businessJpaRepository.getBusinessMaxId() == Integer.MAX_VALUE)
                throw new RuntimeException("id值已经用完！");
            obj.setBid(getBusinessMaxId() + 1);
        }
        businessJpaRepository.save(obj);
        return obj;
    }

    //@Cacheable(value = "query_cache")
    @Override
    public Business qurey(String id) {
        try {
            return businessJpaRepository.findById(Integer.parseInt(id)).get();
        } catch (Exception e) {
            throw new NullPointerException("商户不存在");
        }
    }

    @Override
    public Business update(Business obj) {
        // 覆盖更新
        businessJpaRepository.save(obj);
        return businessJpaRepository.getOne(obj.getBid());
    }

    @Override
    public String delete(String id) {
        businessJpaRepository.deleteById(Integer.parseInt(id));
        return "success";
    }
}
