package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.GenericDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;

public abstract class UserDAO extends GenericDAO<User, Long> {

    public UserDAO(Datasource datasource) {
        super(datasource);
    }

    public abstract User getUserInDB(String firstName, String lastName) throws DAOException;

    public abstract boolean createUser(String firstName, String lastName) throws DAOException;

}