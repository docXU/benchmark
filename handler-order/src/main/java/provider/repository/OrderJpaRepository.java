package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import provider.domain.Order;

import java.util.List;

/**
 * Created by Matt Xu on 2018/3/29
 */

public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
    @Query(value = "select * from tb_order where sid = ?1", nativeQuery = true)
    List<Order> findBySid(int sid);

    @Query(value = "select * from tb_order where bid = ?1", nativeQuery = true)
    List<Order> findByBid(int bid);

    @Query(value = "select * from tb_order where sid = ?1 and bid = ?2", nativeQuery = true)
    List<Order> findBySidAndBid(int sid, int bid);
}
