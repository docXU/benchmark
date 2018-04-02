package provider.domain;

import bigbang.e.AbstractCoupon;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 * Created by Matt Xu on 2018/3/27
 */

public class Coupon extends AbstractCoupon implements Serializable {
    private int cid;

    private int sid;

    private int bid;

    private int type;

    private float discount;

    @Override
    public String toString() {
        return "this is a coupon!";
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @JsonView({View.BusinessView.class, View.ShopperView.class})
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @JsonView({View.BusinessView.class, View.ShopperView.class})
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @JsonView({View.BusinessView.class, View.ShopperView.class})
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @JsonView({View.BusinessView.class, View.ShopperView.class})
    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

}
