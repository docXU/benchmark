package bigbang.i;

import bigbang.e.AbstractOrder;

public interface IOrderService extends ICRUDService {
    AbstractOrder queryBySid(String sid);
    AbstractOrder queryByBid(String bid);
    AbstractOrder queryBySidInBid(String sid, String bid);
}
