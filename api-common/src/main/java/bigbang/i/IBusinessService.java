package bigbang.i;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;

import java.util.Set;

public interface IBusinessService<B> extends ICRUDService<B> {

    AbstractShopper addVIP(String bid, AbstractShopper shopper);

    Set<? extends AbstractShopper> queryAllVIP(String bid);

    AbstractShopper queryVIPDetail(String bid, String sid);

    String deleteVIP(String bid, String sid);

    AbstractCoupon createCoupon(AbstractCoupon coupon);

    String deliverCoupon(String bid, String sid);

    int getBusinessMaxId();

}
