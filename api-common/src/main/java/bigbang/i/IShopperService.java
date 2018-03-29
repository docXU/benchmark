package bigbang.i;

import bigbang.e.AbstractBusiness;
import bigbang.e.AbstractOrder;

import java.util.List;
import java.util.Set;

public interface IShopperService<S> extends ICRUDService<S> {
    Set<? extends AbstractBusiness> queryBusinesses(String sid);

    boolean verifyVIP(String sid, String bid);

    String placeOrder(AbstractOrder order);

    List<? extends AbstractOrder> getOrdersBySid(int sid);

    List<? extends AbstractOrder> getOrdersBySidAndBid(int sid, int bid);
}
