package bigbang.i;

import bigbang.e.AbstractOrder;

import java.util.List;

public interface IOrderService<T> extends ICRUDService<T> {
    List<? extends AbstractOrder> getOrdersBySid(int sid);

    List<? extends AbstractOrder> getOrdersByBid(int bid);

    List<? extends AbstractOrder> getOrdersBySidAndBid(int sid, int bid);

    String placeOrder(AbstractOrder order);

    int getStatus(int oid);
}
