package com.epam.javaee.dao;

import javax.ejb.Local;

@Local
public interface DaoLocal<T> extends Dao<T> {

}
