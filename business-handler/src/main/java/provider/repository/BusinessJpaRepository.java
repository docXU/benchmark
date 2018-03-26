package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import provider.domain.Business;
import provider.domain.Shopper;

import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 */

@Repository
public interface BusinessJpaRepository extends JpaRepository<Business, Integer> {
    // findBy*** 格式 Jpa内部自动实现查询，直接使用即可
    Business findByTelephone(String telephone);

    Set<Shopper> findByBidContaining(@Param("bid") int bid);

    @Query(" select max(id) from #{#entityName} ")
    int getBusinessMaxId();
}
