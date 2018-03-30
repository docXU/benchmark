package provider.domain;

import bigbang.e.AbstractCoupon;
import bigbang.e.AbstractShopper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 */

@Entity
@Table(name = "shopper")
public class Shopper extends AbstractShopper implements Serializable, Cloneable {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shopper:[sid= ").append(this.getSid())
                .append(", nickname=").append(this.getNickname());
        sb.append(", business:[");
        for (Business b :
                businesses) {
            sb.append("business[bid=").append(b.getBid()).append(", shop_name=").append(b.getShop_name()).append("],");
        }
        sb.append("]");

        return sb.toString();
    }

    @Override
    public Shopper clone() {
        Shopper clone = null;
        try {
            clone = (Shopper) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e); // won't happen
        }
        return clone;
    }

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

}
