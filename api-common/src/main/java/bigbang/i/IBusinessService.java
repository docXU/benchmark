package bigbang.i;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;

import java.util.List;
import java.util.Set;

public interface IBusinessService<B> extends ICRUDService<B> {

    AbstractShopper addVIP(String bid, AbstractShopper shopper) throws RuntimeException;

    Set<? extends AbstractShopper> queryAllVIP(String bid) throws RuntimeException;

//    Set<? extends AbstractShopper> queryAllVIPByPage(String bid);

    AbstractShopper queryVIPDetail(String bid, String sid);

    String deleteVIP(String bid, String sid);

    AbstractCoupon createCoupon(AbstractCoupon coupon);

    String deliverCoupon(AbstractCoupon coupons);

    int getBusinessMaxId();

    List<? extends AbstractCoupon> getMyCoupons(int bid);
}
