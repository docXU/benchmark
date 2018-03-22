package provider.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Matt Xu on 2018/3/22
 */

@Entity
@Table(name = "business")
public class Business extends bigbang.e.AbstractBusiness implements Serializable {
    @Id
    private
    int bid;

    @Column
    private
    String shop_name;


    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }


    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
}
