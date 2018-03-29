package provider.service;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;
import bigbang.i.IBusinessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provider.domain.Business;
import provider.domain.Coupon;
import provider.domain.Shopper;
import provider.repository.BusinessJpaRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BusinessServiceImpl implements IBusinessService<Business> {
    private static Logger logger = Logger.getLogger(BusinessServiceImpl.class);

    @Autowired
    BusinessJpaRepository businessJpaRepository;

    @Override
    public Shopper addVIP(String bid, AbstractShopper shopper) throws Exception {
        Shopper instance = (Shopper) shopper;
        Business bus = businessJpaRepository.findById(Integer.parseInt(bid)).get();
        if (!instance.getBusinesses().add(bus)) {
            throw new Exception("用户已经是会员");
        }
        bus.getShoppers().add(instance);
        bus.setLast_update_time(System.currentTimeMillis());
        businessJpaRepository.save(bus);
        return instance;
    }

    @Override
    public Set<Shopper> queryAllVIP(String bid) {
        try {
            Business business = businessJpaRepository.findById(Integer.parseInt(bid)).get();
            return business.getShoppers();
        } catch (Exception e) {
            throw new NullPointerException("不存在商家");
        }
    }

    @Override
    public Shopper queryVIPDetail(String bid, String sid) {
        Set<Shopper> shoppers = queryAllVIP(bid);
        Shopper vip;
        try {
            vip = (Shopper) shoppers.stream()
                    .filter(shopper -> Integer.parseInt(sid) == ((Shopper) shopper).getSid())
                    .findFirst()
                    .get();
            //TODO:优惠券查询
            vip.setCoupons(new HashSet<>(0));
            return vip;
        } catch (Exception e) {
            throw new NullPointerException("不存在的顾客");
        }
    }

    @Override
    public String deleteVIP(String bid, String sid) {
        Business business = query(bid);
        try {
            Set<Shopper> vips = business.getShoppers();
            business.setShoppers(vips.stream()
                    .filter(shopper -> Integer.parseInt(sid) != ((Shopper) shopper).getSid())
                    .collect(Collectors.toSet()));
        } catch (Exception e) {
            throw new NullPointerException("取消失败");
        }
        return "取消成功";
    }

    @Override
    public Coupon createCoupon(AbstractCoupon coupon) {
        Coupon prepareAdd = (Coupon) coupon;
        Business business = businessJpaRepository.getOne(prepareAdd.getBid());
        Set<Coupon> oldCouponSet = business.getCoupons();
        oldCouponSet.add(prepareAdd);
        business.setCoupons(oldCouponSet);
        businessJpaRepository.save(business);
        return (Coupon) coupon;
    }

    @Override
    public String deliverCoupon(String bid, String sid) {
        return "deliver coupon fail";
    }

    @Override
    public int getBusinessMaxId() {
        return businessJpaRepository.getBusinessMaxId();
    }

    @Override
    public Business create(Business obj) {
        Business business = null;
        if (0 == obj.getBid()) {
            int currentMaxID = businessJpaRepository.getBusinessMaxId();
            if (currentMaxID == Integer.MAX_VALUE)
                throw new RuntimeException("id值已经用完！");
            obj.setBid(++currentMaxID);
        }
        try {
            business = businessJpaRepository.save(obj);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            e.printStackTrace();
        }
        return business;
    }

    //@Cacheable(value = "query_cache")
    @Override
    public Business query(String id) {
        try {
            return businessJpaRepository.findById(Integer.parseInt(id)).get();
        } catch (Exception e) {
            throw new NullPointerException("商户不存在");
        }
    }

    @Override
    public Business update(Business obj) {
        // 覆盖更新
        obj.setLast_update_time(System.currentTimeMillis());
        return businessJpaRepository.save(obj);
    }

    @Override
    public String delete(String id) {
        return null;
    }
}
