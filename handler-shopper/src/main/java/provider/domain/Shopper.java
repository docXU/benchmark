package provider.domain;

import bigbang.e.AbstractShopper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 */


@Entity
@Table(name = "shopper")
public class Shopper extends AbstractShopper implements Serializable {
    @Id
    @Column(name = "sid")
    private int sid;
    @Column
    private String nickname;
    @Column
    private String telephone;
    @Column
    private String email;
    @Column
    private int sex;
    @Column
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "shoppers", cascade = CascadeType.REFRESH)
    private Set<Business> businesses;

    @Transient
    private Set<Coupon> coupons;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

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

    public Set<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(Set<Coupon> coupons) {
        this.coupons = coupons;
    }
}
