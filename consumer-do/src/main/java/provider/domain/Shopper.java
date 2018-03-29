package provider.domain;

import bigbang.e.AbstractShopper;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 */

public class Shopper extends AbstractShopper implements Serializable {
    private int sid;
    private String nickname;
    private String telephone;
    private String email;
    private int sex;
    private String password;
    private Set<Business> businesses;
    private Set<Coupon> coupons;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopper:[sid= ").append(this.getSid())
                .append(", nickname=").append(this.getNickname())
                .append(",coupons=[").append(this.getCoupons()).append("]");

        sb.append("business:[");
        for (Business b :
                businesses) {
            sb.append("business[bid=").append(b.getBid()).append(", shop_name=").append(b.getShop_name()).append("],");
        }
        sb.append("]");

        return sb.toString();
    }

    @JsonView(View.ShopperView.class)
    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }

    @JsonView(View.ShopperView.class)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @JsonView(View.ShopperView.class)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @JsonView(View.ShopperView.class)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @JsonView(View.ShopperView.class)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonView(View.ShopperView.class)
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @JsonView(View.ShopperView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Set<Business> businesses) {
        this.businesses = businesses;
    }
}
