package provider.domain;

import bigbang.e.AbstractCoupon;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Matt Xu on 2018/3/26
 * @author xuxiongwei
 */

@Entity
public class Coupon extends AbstractCoupon implements Serializable {
    @Id
    @Column(name = "cid")
    private int cid;

    @Column
    private int sid;

    @Column
    private int bid;

    @Column
    private int type;

    @Column
    private float discount;

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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
