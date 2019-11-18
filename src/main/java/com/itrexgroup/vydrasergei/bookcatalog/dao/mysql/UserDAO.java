package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.GenericDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;

public abstract class UserDAO extends GenericDAO<User, Long> {
    public UserDAO(Datasource datasource) {
        super(datasource);
    }

    public abstract User isUserInDB(String login, long hashPassword) throws DAOException;

    public abstract boolean createUser(User user, long hashPassword) throws DAOException;

    public abstract boolean isLoginInUse(String login) throws DAOException;

    public abstract User update(User user, long hashPassword) throws DAOException;

}