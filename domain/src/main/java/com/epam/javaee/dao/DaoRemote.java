package com.epam.javaee.dao;

import javax.ejb.Remote;

@Remote
public interface DaoRemote<T> extends Dao<T> {

}
