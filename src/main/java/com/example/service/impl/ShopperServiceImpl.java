package com.example.service.impl;

import com.example.domain.Shopper;
import com.example.repository.ShopperJpaRepository;
import com.example.service.IShopperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShopperServiceImpl implements IShopperService {
    @Autowired
    ShopperJpaRepository shopperJpaRepository;

    @Override
    public List<Shopper> findAll() {
        return shopperJpaRepository.findAll();
    }

    //@Cacheable("pages")
    @Override
    public Page<Shopper> findAllByPage(Pageable p) {
        return shopperJpaRepository.findAll(p);
    }

    @Override
    public void saveUser(Shopper shopper) {
        shopperJpaRepository.save(shopper);
    }

    @Cacheable("shopper")
    @Override
    public Shopper findOne(int id) {
        return shopperJpaRepository.findOne(id);
    }

    @Override
    public void delete(int id) {
        shopperJpaRepository.delete(id);
    }

    @Cacheable("shoppers")
    @Override
    public List<Shopper> findByNickname(String nickname) {
        return shopperJpaRepository.findByNickName(nickname);

    }

    @Override
    public List<Shopper> findByTel(String telephone) {
        return shopperJpaRepository.findByTel(telephone);
    }
}
