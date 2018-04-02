package provider.domain;

import bigbang.e.AbstractOrder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matt Xu on 2018/3/29
 */

public class Order extends AbstractOrder implements Serializable {
    private int oid;
    private int sid;
    private Integer cid;
    private int bid;
    private Date create_time;
    private int last_update_time;
    private float cost;
    private boolean use_coupons;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(int last_update_time) {
        this.last_update_time = last_update_time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isUse_coupons() {
        return use_coupons;
    }

    public void setUse_coupons(boolean use_coupons) {
        this.use_coupons = use_coupons;
    }
}
