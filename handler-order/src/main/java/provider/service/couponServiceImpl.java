package provider.service;

import bigbang.i.ICouponSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import provider.domain.Coupon;
import provider.repository.CouponJpaRepository;

import java.util.List;

/**
 * Created by Matt Xu on 2018/3/30
 */

public class couponServiceImpl implements ICouponSerivce<Coupon> {

    @Autowired
    CouponJpaRepository couponJpaRepository;

    @Override
    public List<Coupon> findBySid(int sid) {
        return couponJpaRepository.findBySid(sid);
    }

    @Override
    public int getMaxId() {
        return couponJpaRepository.getMaxId();
    }

    @Override
    public Coupon create(Coupon obj) {
        return null;
    }

    @Override
    public Coupon query(String id) {
        return null;
    }

    @Override
    public Coupon update(Coupon obj) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }


}
