package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.GenericDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.UserBook;

import java.util.List;

public abstract class UserBookDAO extends GenericDAO<UserBook, Long> {

    public UserBookDAO(Datasource datasource) {
        super(datasource);
    }

    public abstract boolean createByIds(Long userId, Long bookId) throws DAOException;

}