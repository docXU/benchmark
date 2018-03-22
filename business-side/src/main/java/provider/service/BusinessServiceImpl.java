package provider.service;

import bigbang.e.AbstractBusiness;
import bigbang.e.AbstractCoupon;
import bigbang.i.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import provider.domain.Business;
import provider.domain.Shopper;
import provider.repository.BusinessJpaRepository;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BusinessServiceImpl implements IBusinessService<Business> {

    @Autowired
    BusinessJpaRepository businessJpaRepository;

    @Override
    public List<Shopper> queryAllVIP(String bid) {
        return null;
    }

    @Override
    public Shopper queryVIPDetail(String bid, String sid) {
        return null;
    }

    @Override
    public String deleteVIP(String bid, String sid) {
        return null;
    }

    @Override
    public AbstractCoupon createCoupon(Map params) {
        return null;
    }

    @Override
    public String deliverCoupon(String bid, String sid) {
        return null;
    }

    @Override
    public Business create(Business obj) {
        businessJpaRepository.save(obj);
        return null;
    }

    @Override
    public Business qurey(String id) {
        // bid是int,so do
        return businessJpaRepository.findById(Integer.parseInt(id)).get();
    }

    @Override
    public Business update(Business obj) {
        // 覆盖更新
        businessJpaRepository.save(obj);
        return businessJpaRepository.getOne(obj.getBid());
    }

    @Override
    public String delete(String id) {
        businessJpaRepository.deleteById(Integer.parseInt(id));
        return "success";
    }
}
