package runner.util;

import org.springframework.data.redis.core.RedisTemplate;
import provider.domain.Order;

/**
 * Created by Matt Xu on 2018/4/8
 *
 * @author xuxiongwei
 */

public class RedisHandler {
    public static void refreshRedisCache(RedisTemplate redisTemplate, Order order) {
        int year = order.getCreate_time().getYear() + 1900;
        int bid = order.getBid();
        int sid = order.getSid();
        String deleteShopperKey = "getShopperTotalInYear" + sid + "year" + year;
        String deleteBusinessKey = "getBusinessTotalInYear" + bid + "year" + year;

        redisTemplate.delete(deleteShopperKey);
        redisTemplate.delete(deleteBusinessKey);
    }
}
