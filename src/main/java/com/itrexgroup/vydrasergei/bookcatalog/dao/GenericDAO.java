package com.itrexgroup.vydrasergei.bookcatalog.dao;

import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;

import java.util.List;

public abstract class GenericDAO<O, K> {
    protected Datasource datasource;

    public GenericDAO(Datasource datasource) {
        this.datasource = datasource;
    }

    public abstract O create(O entity) throws DAOException;

    public abstract O findById(K id) throws DAOException;

    public abstract List<O> findAll() throws DAOException;

    public abstract boolean update(O entity) throws DAOException;

    public abstract boolean delete(K id) throws DAOException;
}
