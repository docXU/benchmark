package provider.service;

import bigbang.i.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import provider.domain.Shopper;
import provider.repository.ShopperJpaRepository;

/**
 * Created by Matt Xu on 2018/3/27
 */

public class ShopperServiceImpl implements IShopperService<Shopper> {

    @Autowired
    ShopperJpaRepository shopperJpaRepository;

    @Override
    public Shopper create(Shopper obj) {
        return shopperJpaRepository.save(obj);
    }

    @Override
    public Shopper qurey(String id) {
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
