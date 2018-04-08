package bigbang.i;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractOrder;

import java.util.Date;
import java.util.List;

public interface IOrderService<T> extends ICRUDService<T> {
    List<T> getOrdersBySid(int sid);

    List<T> getOrdersByBid(int bid);

    List<T> getOrdersBySidAndBid(int sid, int bid);

    List<T> getOrdersBySidBetweenTime(int sid, Date startTime, Date endTime);

    List<T> getOrdersByBidBetweenTime(int bid, Date startTime, Date endTime);

    T placeOrder(T order);

    int getStatus(int oid);

    //TODO: 优惠券服务分离订单服务
    AbstractCoupon createCoupon(AbstractCoupon coupon);

    List<? extends AbstractCoupon> getCouponsBySid(int sid);

    List<? extends AbstractCoupon> getCouponsByBid(int bid);

    int getMaxId();
}
