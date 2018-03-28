package bigbang.e;

import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/26
 */

//出现Dubbo反序列化失败异常，后续处理，现不用
public interface VIPable {
    Set<? extends AbstractCoupon> getCoupons();

    void setCoupons(Set<? extends AbstractCoupon> coupons);
}
