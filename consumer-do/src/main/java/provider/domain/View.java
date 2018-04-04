package provider.domain;

/**
 * Created by Matt Xu on 2018/3/28
 */

public class View {

    //Shopper json序列化视图用于隔离Business属性
    public interface ShopperView {
    }

    //Business 隔离Shopper属性避免循环
    public interface BusinessView {
    }

    //Order 隔离主键信息
    public interface OrderView {
    }
}
