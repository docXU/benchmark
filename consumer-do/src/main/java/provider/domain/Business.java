package provider.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Matt Xu on 2018/3/22
 */
public class Business extends bigbang.e.AbstractBusiness implements Serializable {
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

    public int getCity_code() {
        return city_code;
    }

    public void setCity_code(int city_code) {
        this.city_code = city_code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Business:[bid= ").append(this.getBid())
                .append(", shop_name").append(this.getShop_name())
                .append(", create_time=").append(this.getCreate_time())
                .append(", Shoppers:[");
        for (Shopper s:
             shoppers) {
            sb.append("Shopper:[sid= ").append(s.getSid())
                    .append(", nickname=").append(s.getNickname()).append("],");
        }
        sb.append("]");
        return sb.toString();
    }
}
