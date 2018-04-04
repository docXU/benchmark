package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import provider.domain.Order;

import java.util.Date;
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

    @Query(value = "from #{#entityName} o where o.sid = :sid and o.create_time between :startDate and :endDate")
    List<Order> findBySidBetweenTime(@Param("sid") int sid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "from #{#entityName} o where o.bid = :bid and o.create_time between :startDate and :endDate")
    List<Order> findByBidBetweenTime(@Param("bid") int bid, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
