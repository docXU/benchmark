package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import provider.domain.Order;

import java.util.List;

/**
 * Created by Matt Xu on 2018/3/29
 */

public interface OrderJpaRepository extends JpaRepository<Order, Integer> {
    @Query(value = "from #{#entityName} where sid = ?1")
    List<Order> findBySid(int sid);

    @Query(value = "from #{#entityName} where bid = ?1")
    List<Order> findByBid(int bid);

    @Query(value = "from #{#entityName} where sid = ?1 and bid = ?2")
    List<Order> findBySidAndBid(int sid, int bid);
}
