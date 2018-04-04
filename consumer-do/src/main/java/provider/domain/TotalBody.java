package provider.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Matt Xu on 2018/4/4
 *
 * @author xuxiongwei
 */

public class TotalBody implements Serializable {
    private int id;
    private TotalType type;
    private String which;
    private float allSummary = .0f;
    private int allCount = 0;
    private Item[] itemList;

    public TotalBody(int id, TotalType type, String which, List<Order> orders) {
        this.id = id;
        this.allCount = orders.size();
        this.type = type;
        this.which = which;
        switch (type) {
            case ALL:
                //总计先不管
                break;
            case YEAR:
                itemList = new Item[12];
                break;
            case MOUTH:
                itemList = new Item[31];
                break;
            case DAY:
                itemList = new Item[24];
                break;
            default:
                itemList = null;
        }
        for (Order order : orders) {
            allSummary += order.getCost();
            reduceOrderToItemList(order);
        }

    }

    @Override
    public String toString() {
        return "total toString";
    }

    private void reduceOrderToItemList(Order order) {
        int title = 0;
        System.out.println(order.getCreate_time());
        switch (this.type) {
            case ALL:
                //title = order.getCreate_time().getYear();
                break;
            case YEAR:
                title = order.getCreate_time().getMonth() + 1;
                break;
            case MOUTH:
                title = order.getCreate_time().getDate();
                break;
            case DAY:
                title = order.getCreate_time().getHours();
                break;
            default:
                break;
        }
        Item item = itemList[title - 1];
        if (item == null) {
            item = new Item();
            item.title = title;
            itemList[title - 1] = item;
        }

        //TODO:原子性
        item.summary += order.getCost();
        item.count++;
    }

    class Item implements Serializable {
        int title;
        float summary;
        int count;

        public int getTitle() {
            return title;
        }

        public void setTitle(int title) {
            this.title = title;
        }

        public float getSummary() {
            return summary;
        }

        public void setSummary(float summary) {
            this.summary = summary;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    public enum TotalType {
        ALL,
        YEAR,
        MOUTH,
        DAY
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TotalType getType() {
        return type;
    }

    public void setType(TotalType type) {
        this.type = type;
    }

    public String getWhich() {
        return which;
    }

    public void setWhich(String which) {
        this.which = which;
    }

    public float getAllSummary() {
        return allSummary;
    }

    public void setAllSummary(float allSummary) {
        this.allSummary = allSummary;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public Item[] getItemList() {
        return itemList;
    }
}
