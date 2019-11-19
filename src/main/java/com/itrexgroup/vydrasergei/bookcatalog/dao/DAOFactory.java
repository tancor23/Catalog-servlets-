package com.itrexgroup.vydrasergei.bookcatalog.dao;

import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.MysqlDataSource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory instance;
    private Datasource datasource;

    private final UserDAO userDAO;

    private DAOFactory() {
        datasource = MysqlDataSource.getInstance();
        userDAO = new UserDAOImpl(datasource);

    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

}
