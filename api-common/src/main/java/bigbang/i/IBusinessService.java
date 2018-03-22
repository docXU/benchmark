package bigbang.i;

import bigbang.e.AbstractBusiness;
import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;

import java.util.List;
import java.util.Map;

public interface IBusinessService<B> extends ICRUDService<B> {

    /**
     * 查询商户的会员列表
     * @param bid 商户id
     * @return 会员列表
     */
    <T extends AbstractShopper>List<T> queryAllVIP(String bid);

    /**查询商户的某个会员的详情信息
     * @param bid 商户id
     * @param sid 顾客id
     * @return 会员详情
     */
    <T extends AbstractShopper>T queryVIPDetail(String bid, String sid);


    /**
     * 删除会员
     * @param bid 商户id
     * @param sid 顾客id
     * @return 操作结果
     */
    String deleteVIP(String bid, String sid);


    /**
     * 创建优惠券
     * @param params 参数报文
     * @return 优惠券详情
     */
    AbstractCoupon createCoupon(Map params);

    /**
     * 赠送优惠券
     * @param bid 商家id
     * @param sid 顾客id
     * @return 操作结果
     */
    String deliverCoupon(String bid, String sid);


}
