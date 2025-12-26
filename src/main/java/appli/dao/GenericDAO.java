package appli.dao;


import java.util.List;

public interface GenericDAO <T> {

    List<T> getAll();
    T getById(int id);
    void insert(T t);
    void delete(int id);
    void update(T toUpdate);
}
