package com.example.repository;

import com.example.domain.Shopper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopperJpaRepository extends JpaRepository<Shopper, Integer> {
    @Query(value = "select * from #{#entityName} s where s.nickname=?1", nativeQuery = true)
    public List<Shopper> findByNickName(String nickname);

    @Query(value = "select * from #{#entityName} s where s.telephone=?1", nativeQuery = true)
    public List<Shopper> findByTel(String Tel);
}
