package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import provider.domain.Shopper;

/**
 * Created by Matt Xu on 2018/3/27
 */


public interface ShopperJpaRepository extends JpaRepository<Shopper, Integer> {
}
