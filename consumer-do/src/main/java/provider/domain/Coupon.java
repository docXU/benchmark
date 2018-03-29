package provider.domain;

import bigbang.e.AbstractCoupon;

import java.io.Serializable;

/**
 * Created by Matt Xu on 2018/3/27
 */

public class Coupon extends AbstractCoupon implements Serializable {
    private int cid;

    private int sid;

    private int bid;

    private int type;

    private float discout;

    private Business business;

    private Shopper shopper;

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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getDiscout() {
        return discout;
    }

    public void setDiscout(float discout) {
        this.discout = discout;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public Shopper getShopper() {
        return shopper;
    }

    public void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }


}
