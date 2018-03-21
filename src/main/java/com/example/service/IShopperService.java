package com.example.service;

import com.example.domain.Shopper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IShopperService {
    List<Shopper> findAll();

    Page<Shopper> findAllByPage(Pageable p);

    void saveUser(Shopper book);

    Shopper findOne(int id);

    void delete(int id);

    List<Shopper> findByNickname(String name);

    List<Shopper> findByTel(String telephone);
}
