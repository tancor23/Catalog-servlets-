package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.GenericDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.UserBook;

public abstract class UserBookDAO extends GenericDAO<UserBook, Long> {

    public UserBookDAO(Datasource datasource) {
        super(datasource);
    }

    public abstract boolean createByIds(Long userId, Long bookId) throws DAOException;

}