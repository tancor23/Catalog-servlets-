package com.itrexgroup.vydrasergei.bookcatalog.dao;

import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAO<O, K> {
    protected Datasource datasource;

    public GenericDAO(Datasource datasource) {
        this.datasource = datasource;
    }

    public O create(O entity) throws DAOException {
        try (Connection connection = datasource.getConnection()) {
            return create(entity, connection);
        } catch (InterruptedException | SQLException e) {
            throw new DAOException("DB connection error", e);
        }
    }

    public abstract O create(O entity, Connection connection) throws DAOException;

    public abstract O findById(K id) throws DAOException;

    public abstract List<O> findAll() throws DAOException;

    public abstract O update(O entity) throws DAOException;

    public abstract boolean delete(K id) throws DAOException;
}
