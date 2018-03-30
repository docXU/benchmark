package bigbang.i;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractOrder;

import java.util.List;

public interface IOrderService<T> extends ICRUDService<T> {
    List<T> getOrdersBySid(int sid);

    List<T> getOrdersByBid(int bid);

    List<T> getOrdersBySidAndBid(int sid, int bid);

    String placeOrder(T order);

    int getStatus(int oid);

    //TODO: 优惠券服务分离订单服务
    AbstractCoupon createCoupon(AbstractCoupon coupon);

    List<? extends AbstractCoupon> getCouponsBySid(int sid);

    List<? extends AbstractCoupon> getCouponsByBid(int bid);
}
