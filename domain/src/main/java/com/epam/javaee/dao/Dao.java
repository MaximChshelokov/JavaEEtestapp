package com.epam.javaee.dao;

import java.util.List;

public interface Dao <T> {

    boolean create(T object);
    T read(long id);
    boolean update(T object);
    boolean delete(long id);
    List<T> getAll();
}
