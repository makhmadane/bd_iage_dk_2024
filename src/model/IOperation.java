package model;

import java.sql.SQLException;
import java.util.List;

public interface IOperation<T> {

    void  add(T t) throws SQLException;
    void update(int id,T t) throws SQLException;
    void delete(int id) throws SQLException;
    T findById(int id) throws SQLException;

    List<T> findByNumero(String numero) throws SQLException;
    List<T> findAll() throws SQLException;
}
