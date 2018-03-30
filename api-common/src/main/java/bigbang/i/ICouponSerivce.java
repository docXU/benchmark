package bigbang.i;

import bigbang.e.AbstractCoupon;

import java.util.List;

/**
 * Created by Matt Xu on 2018/3/30
 */

public interface ICouponSerivce<T> extends ICRUDService<T> {
    List<T> findBySid(int sid);
}
