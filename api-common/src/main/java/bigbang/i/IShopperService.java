package bigbang.i;

import bigbang.e.AbstractBusiness;

import java.util.Set;

public interface IShopperService<S> extends ICRUDService<S> {
    Set<? extends AbstractBusiness> queryBusinesses(String sid);

    boolean verifyVIP(String sid, String bid);
}
