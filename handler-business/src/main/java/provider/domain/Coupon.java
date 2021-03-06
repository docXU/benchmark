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

    @Column
    private int sid;

    //TODO:约束business的coupon，使得coupon的插入更新只能通过business类操作
    @Column
    private int bid;

    /**
     * 0代表是减额，1代表是打折
     */
    @Column
    private int type;

    @Column
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
