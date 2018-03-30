package bigbang.i;

import bigbang.e.AbstractBusiness;
import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractOrder;

import java.util.List;
import java.util.Set;

public interface IShopperService<S> extends ICRUDService<S> {
    Set<? extends AbstractBusiness> queryBusinesses(String sid);

    boolean verifyVIP(String sid, String bid);

    String placeOrder(AbstractOrder order);

    List<? extends AbstractOrder> getMyAllOrders(int sid);

    List<? extends AbstractOrder> getMyOrdersWithBusiness(int sid, int bid);

    List<? extends AbstractCoupon> getMyCoupons(int sid);
}
