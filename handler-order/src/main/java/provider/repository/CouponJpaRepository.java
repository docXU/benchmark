package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import provider.domain.Coupon;

import java.util.List;

/**
 * Created by Matt Xu on 2018/3/30
 */

public interface CouponJpaRepository extends JpaRepository<Coupon, Integer> {
    @Query(value = "select * from coupon where sid = ?1", nativeQuery = true)
    List<Coupon> findBySid(int sid);

    @Query(value = "select * from coupon where bid = ?1", nativeQuery = true)
    List<Coupon> findByBid(int bid);
}
