package bigbang.i;

import java.util.Map;

public interface ICRUDService<T> {
    T create(T obj);
    T qurey(String id);
    //如果已存在相同主键数据，则根据主键值覆盖更新
    T update(T obj);
    String delete(String id);
}
