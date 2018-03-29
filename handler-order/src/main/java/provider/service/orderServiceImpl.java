package provider.service;

import bigbang.e.AbstractOrder;
import bigbang.i.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import provider.domain.Order;
import provider.repository.OrderJpaRepository;

import java.util.List;

/**
 * Created by Matt Xu on 2018/3/29
 */

public class orderServiceImpl implements IOrderService<Order> {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

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
    public String placeOrder(AbstractOrder order) {
        try {
            create((Order) order);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
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
