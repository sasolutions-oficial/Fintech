package DAO;

import Model.User;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(String id);
    List<T> getAll();
    List<T> getAll(String id);
    void save(T t);
//    void update(T t, String[] params);
    void delete(String id);
}
