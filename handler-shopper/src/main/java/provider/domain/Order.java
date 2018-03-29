package provider.domain;

import bigbang.e.AbstractOrder;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Matt Xu on 2018/3/29
 */

public class Order extends AbstractOrder implements Serializable {
    private int oid;
    private int sid;
    private int bid;
    private Date create_time;
    private int last_update_time;
    private float cost;
    private boolean use_coupons;
    private Integer cid;
}
