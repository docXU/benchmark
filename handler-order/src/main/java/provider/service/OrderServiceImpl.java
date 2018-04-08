package provider.service;

import bigbang.e.AbstractCoupon;
import bigbang.i.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import provider.domain.Coupon;
import provider.domain.Order;
import provider.repository.CouponJpaRepository;
import provider.repository.OrderJpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Matt Xu on 2018/3/29
 * @author xuxiongwei
 */

public class OrderServiceImpl implements IOrderService<Order> {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Autowired
    private CouponJpaRepository couponJpaRepository;

    @Override
    public List<Order> getOrdersBySid(int sid) {
        return orderJpaRepository.findBySid(sid);
    }

    @Override
    public List<Order> getOrdersByBid(int bid) {
        return orderJpaRepository.findByBid(bid);
    }

    @Override
    public List<Order> getOrdersBySidAndBid(int sid, int bid) {
        return orderJpaRepository.findBySidAndBid(sid, bid);
    }

    @Override
    public List<Order> getOrdersBySidBetweenTime(int sid, Date startTime, Date endTime) {
        return orderJpaRepository.findBySidBetweenTime(sid, startTime, endTime);
    }

    @Override
    public List<Order> getOrdersByBidBetweenTime(int bid, Date startTime, Date endTime) {
        return orderJpaRepository.findByBidBetweenTime(bid, startTime, endTime);
    }

    @Override
    public Order placeOrder(Order order) {
        try {
            order.setOid(getMaxId() + 1);
            return create(order);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getStatus(int oid) {
        try {
            Order order = query(String.valueOf(oid));
            return order.getStatus();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Coupon createCoupon(AbstractCoupon coupon) {
        try {
            Coupon ins = (Coupon) coupon;
            ins.setCid(couponJpaRepository.getMaxId() + 1);
            return couponJpaRepository.save(ins);
        } catch (Exception e) {
            //抛出id用完等异常
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Coupon> getCouponsBySid(int sid) {
        return couponJpaRepository.findBySid(sid);
    }

    @Override
    public List<Coupon> getCouponsByBid(int bid) {
        return couponJpaRepository.findByBid(bid);
    }

    @Override
    public int getMaxId() {
        System.out.println(orderJpaRepository.getMaxId());
        return orderJpaRepository.getMaxId();
    }

    @Override
    public Order create(Order obj) {
        return orderJpaRepository.save(obj);
    }

    @Override
    public Order query(String oid) {
        try {
            return orderJpaRepository.findById(Integer.valueOf(oid)).get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order update(Order obj) {
        return orderJpaRepository.save(obj);
    }

    @Override
    public String delete(String oid) {
        try {
            orderJpaRepository.deleteById(Integer.valueOf(oid));
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
