package provider.domain;

import bigbang.e.AbstractCoupon;

import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/26
 */
public class VIP extends Shopper {
    Set<? extends AbstractCoupon> coupons;

    public Set<? extends AbstractCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<? extends AbstractCoupon> coupons) {
        this.coupons = coupons;
    }
}
