package provider.domain;


import bigbang.e.AbstractBusiness;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 * @author xuxiongwei
 */
public class Business extends AbstractBusiness implements Serializable {
    private int bid;

    private String shop_name;
    private String address;
    private String telephone;
    private int type;
    private String password;
    private Date create_time;
    private long last_update_time;
    private int city_code;
    private Set<Shopper> shoppers;
    private Set<Coupon> coupons;

    @JsonView(View.BusinessView.class)
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

    @JsonView(View.BusinessView.class)
    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    @JsonView(View.BusinessView.class)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @JsonView(View.BusinessView.class)
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @JsonView(View.BusinessView.class)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonView(View.BusinessView.class)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @JsonView(View.BusinessView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonView(View.BusinessView.class)
    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public long getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(long last_update_time) {
        this.last_update_time = last_update_time;
    }

    @JsonView(View.BusinessView.class)
    public int getCity_code() {
        return city_code;
    }

    public void setCity_code(int city_code) {
        this.city_code = city_code;
    }
}
