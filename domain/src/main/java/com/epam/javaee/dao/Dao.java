package com.epam.javaee.dao;

import java.util.List;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

public interface Dao <T> {

    boolean create(T object);
    T read(long id);
    boolean update(T object);
    boolean delete(long id);
    List<T> getAll();
}
