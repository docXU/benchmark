package bigbang.i;

public interface ICRUDService<T> {
    T create(T obj);

    T qurey(String id);

    T update(T obj);

    String delete(String id);
}
