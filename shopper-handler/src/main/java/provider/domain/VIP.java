package provider.domain;

import bigbang.e.AbstractCoupon;

import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/26
 */


public class VIP extends Shopper {
    @OneToMany(mappedBy = "VIP")
    private Set<Coupon> coupons;

    public Set<? extends AbstractCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }
}
