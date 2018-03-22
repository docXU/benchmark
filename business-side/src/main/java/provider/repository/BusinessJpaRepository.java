package provider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import provider.domain.Business;

import java.util.Map;

/**
 * Created by Matt Xu on 2018/3/22
 */

public interface BusinessJpaRepository extends JpaRepository<Business, Integer> {

}
