package provider.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 */

@Entity
@Table(name = "business")
public class Business extends bigbang.e.AbstractBusiness implements Serializable {
    @Id
    @Column(name = "bid")
    private int bid;

    @Column
    private String shop_name;

    @Column
    private String address;

    @Column
    private String telephone;

    @Column
    private int type;

    @Column
    private String password;

    @Column
    private Date create_time;

    @Column
    private long last_update_time;

    @Column
    private int city_code;

    //TODO:取消试用内置的方式，后期自己完成级联逻辑避免性能消耗
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "contact_bus_sho", joinColumns = {
            @JoinColumn(name = "bid", referencedColumnName = "bid")}, inverseJoinColumns = {
            @JoinColumn(name = "sid", referencedColumnName = "sid")})
    private Set<Shopper> shoppers = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "business", cascade = CascadeType.REFRESH)
    private Set<Coupon> coupons = new HashSet<>();

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Set<Shopper> getShoppers() {
        return shoppers;
    }

    public void setShoppers(Set<Shopper> shoppers) {
        this.shoppers = shoppers;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(long last_update_time) {
        this.last_update_time = last_update_time;
    }

    public int getCity_code() {
        return city_code;
    }

    public void setCity_code(int city_code) {
        this.city_code = city_code;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
