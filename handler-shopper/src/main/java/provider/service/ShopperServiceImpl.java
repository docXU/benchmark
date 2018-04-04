package provider.service;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractOrder;
import bigbang.i.ICouponSerivce;
import bigbang.i.IOrderService;
import bigbang.i.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import provider.domain.Business;
import provider.domain.Coupon;
import provider.domain.Order;
import provider.domain.Shopper;
import provider.repository.ShopperJpaRepository;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/27
 * @author xuxiongwei
 */

public class ShopperServiceImpl implements IShopperService<Shopper> {

    @Autowired
    private ShopperJpaRepository shopperJpaRepository;

    @Resource
    private IOrderService orderService;

    @Override
    public Set<Business> queryBusinesses(String sid) {
        return query(sid).getBusinesses();
    }

    @Override
    public boolean verifyVIP(String sid, String bid) {
        Shopper shopper = query(sid);
        try {
            return shopper.getBusinesses().stream().anyMatch(business -> business.getBid() == Integer.parseInt(bid));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String placeOrder(AbstractOrder order) {
        return orderService.placeOrder((Order) order);
    }

    @Override
    public List<Coupon> getMyCoupons(int sid) {
        return orderService.getCouponsBySid(sid);
    }

    @Override
    public List<Order> getMyAllOrders(int sid) {
        return orderService.getOrdersBySid(sid);
    }

    @Override
    public List<Order> getMyOrdersWithBusiness(int sid, int bid) {
        return orderService.getOrdersBySidAndBid(sid, bid);
    }

    @Override
    public Shopper create(Shopper obj) {
        return shopperJpaRepository.save(obj);
    }

    @Override
    public Shopper query(String id) {
        Shopper shopper = shopperJpaRepository.findById(Integer.parseInt(id)).get();
        shopper.setCoupons(new HashSet<Coupon>(orderService.getCouponsBySid(shopper.getSid())));
        return shopper;
    }

    @Override
    public Shopper update(Shopper obj) {
        return shopperJpaRepository.save(obj);
    }

    @Override
    public String delete(String id) {
        shopperJpaRepository.deleteById(Integer.parseInt(id));
        return "done";
    }
}
