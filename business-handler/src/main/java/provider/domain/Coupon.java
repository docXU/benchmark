package provider.domain;

import bigbang.e.AbstractCoupon;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Matt Xu on 2018/3/26
 */

@Entity
public class Coupon extends AbstractCoupon implements Serializable {
    @Id
    @Column(name = "cid")
    private int cid;

    @Column(insertable = false, updatable = false)
    private int sid;

    //约束business的coupon，使得coupon的插入更新只能通过business类操作
    @Column(insertable = false, updatable = false)
    private int bid;

    @Column
    private int type;

    @Column
    private float discout;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Business.class)
    @JoinColumn(name = "bid")
    private Business business;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Shopper.class)
    @JoinColumn(name = "sid")
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
