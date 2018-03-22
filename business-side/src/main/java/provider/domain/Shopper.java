package provider.domain;

import bigbang.e.AbstractShopper;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Matt Xu on 2018/3/22
 */


@Entity
public class Shopper extends AbstractShopper {
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Id
    int sid;

}
