package provider.service;

import bigbang.e.AbstractOrder;
import bigbang.i.IOrderService;
import bigbang.i.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import provider.domain.Business;
import provider.domain.Order;
import provider.domain.Shopper;
import provider.repository.ShopperJpaRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/27
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
    public List<Order> getOrdersBySid(int sid) {
        return orderService.getOrdersBySid(sid);
    }

    @Override
    public List<Order> getOrdersBySidAndBid(int sid, int bid) {
        return orderService.getOrdersBySidAndBid(sid, bid);
    }

    @Override
    public Shopper create(Shopper obj) {
        return shopperJpaRepository.save(obj);
    }

    @Override
    public Shopper query(String id) {
        return shopperJpaRepository.findById(Integer.parseInt(id)).get();
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
