package provider.service;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;
import bigbang.i.IBusinessService;
import bigbang.i.IOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provider.domain.Business;
import provider.domain.Coupon;
import provider.domain.Shopper;
import provider.repository.BusinessJpaRepository;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuxiongwei
 */
@Service
public class BusinessServiceImpl implements IBusinessService<Business> {
    private static Logger logger = Logger.getLogger(BusinessServiceImpl.class);

    @Autowired
    private BusinessJpaRepository businessJpaRepository;

    @Resource
    private IOrderService orderService;

    @Override
    @CacheEvict(value = "viplist", key = "'queryAllVIP' + #bid ")
    public Shopper addVIP(String bid, AbstractShopper shopper) {
        Shopper instance = (Shopper) shopper;
        Business bus = businessJpaRepository.findById(Integer.parseInt(bid)).get();
        if (!instance.getBusinesses().add(bus)) {
            throw new RuntimeException("用户已经是会员");
        }
        bus.getShoppers().add(instance);
        bus.setLast_update_time(System.currentTimeMillis());
        businessJpaRepository.save(bus);
        return instance;
    }

    @Override
    @Transactional
    @Cacheable(value = "viplist", key = "'queryAllVIP' + #bid")
    public Set<Shopper> queryAllVIP(String bid) {
        try {
            Business business = businessJpaRepository.findById(Integer.parseInt(bid)).get();
            for (Shopper shopper : business.getShoppers()) {
                shopper.setCoupons(new HashSet<Coupon>(orderService.getCouponsBySid(shopper.getSid())));
            }
            return business.getShoppers();
        } catch (Exception e) {
            throw new RuntimeException("不存在商家");
        }
    }

    @Override
    public Shopper queryVIPDetail(String bid, String sid) {
        try {
            Set<Shopper> shoppers = queryAllVIP(bid);
            Shopper vip;
            vip = shoppers.stream()
                    .filter(shopper -> Integer.parseInt(sid) == ((Shopper) shopper).getSid())
                    .findFirst()
                    .get();
            //TODO:优惠券查询
            vip.setCoupons(new HashSet<Coupon>(orderService.getCouponsBySid(vip.getSid())));
            return vip;
        } catch (Exception e) {
            throw new RuntimeException("不存在的顾客");
        }
    }

    @Override
    @CacheEvict(value = "viplist", key = "'queryAllVIP' + #bid ")
    public String deleteVIP(String bid, String sid) {
        Business business = query(bid);
        try {
            Set<Shopper> vips = business.getShoppers();
            business.setShoppers(vips.stream()
                    .filter(shopper -> Integer.parseInt(sid) != shopper.getSid())
                    .collect(Collectors.toSet()));
        } catch (Exception e) {
            throw new RuntimeException("不存在的vip");
        }
        return "取消成功";
    }

    @Override
    public Coupon createCoupon(AbstractCoupon coupon) {
        try {
            return (Coupon) orderService.createCoupon(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("违法的参数列表");
        }
    }

    @Override
    public List<Coupon> getMyCoupons(int bid) {
        return orderService.getCouponsByBid(bid);
    }

    @Override
    public String deliverCoupon(AbstractCoupon coupon) {
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
            if (currentMaxID == Integer.MAX_VALUE) {
                throw new RuntimeException("id值已经用完！");
            }
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

    @Override
    public Business query(String id) {
        try {
            Business business = businessJpaRepository.findById(Integer.parseInt(id)).get();
            business.setCoupons(new HashSet(orderService.getCouponsByBid(business.getBid())));
            return business;
        } catch (Exception e) {
            throw new RuntimeException("商户不存在");
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
