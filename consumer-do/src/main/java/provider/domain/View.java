package provider.domain;

/**
 * Created by Matt Xu on 2018/3/28
 */

public class View {

    //VIP json序列化视图用于隔离Business属性
    public interface VIPView {
    }

    interface ShopperDetailView extends VIPView {
    }

    public interface BusinessView {
    }
}
