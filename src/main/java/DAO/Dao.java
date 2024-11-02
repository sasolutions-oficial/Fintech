package DAO;

//import Model.User;
import Model.ReturnData;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> get(String id);
    List<T> getAll();
    List<T> getAll(String id);
    ReturnData save(T t);
//    void update(T t, String[] params);
    ReturnData delete(String id);
}
