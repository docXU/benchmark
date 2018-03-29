package provider.domain;

import bigbang.e.AbstractOrder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matt Xu on 2018/3/29
 */

@Entity
public class Order extends AbstractOrder implements Serializable {
    @Id
    private int oid;

    @Column
    private int sid;

    @Column
    private int bid;

    @Column
    private Date create_time;

    @Column
    private int last_update_time;

    @Column
    private int cost;

    @Column
    private boolean use_coupons;

    @Column
    private int cid;

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

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isUse_coupons() {
        return use_coupons;
    }

    public void setUse_coupons(boolean use_coupons) {
        this.use_coupons = use_coupons;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
