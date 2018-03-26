package provider.domain;

import bigbang.e.AbstractShopper;

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Shopper:[sid= ").append(this.getSid()).append(", nickname=").append(this.getNickname()).append(", ");

        sb.append("business:[");
        for (Business b:
             businesses) {
            sb.append("business[bid=").append(b.getBid()).append(", shop_name=").append(b.getShop_name()).append("],");
        }
        sb.append("]");

        return sb.toString();
    }
}
