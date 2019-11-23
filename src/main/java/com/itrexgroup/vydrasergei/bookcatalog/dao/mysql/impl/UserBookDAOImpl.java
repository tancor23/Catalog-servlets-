package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserBookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.UserBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class UserBookDAOImpl extends UserBookDAO {
    private static final String ADD_USER_BOOK_BY_IDS_SQL = "INSERT INTO user_book(user_id, book_id) VALUES(?,?);";

    public UserBookDAOImpl(Datasource datasource) {
        super(datasource);
    }

    @Override
    public UserBook create(UserBook entity) throws DAOException {
        return null;
    }

    @Override
    public UserBook findById(Long id) throws DAOException {
        return null;
    }

    @Override
    public List<UserBook> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(UserBook entity) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }

    @Override
    public boolean createByIds(Long userId, Long bookId) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(ADD_USER_BOOK_BY_IDS_SQL);) {
            ps.setLong(1, userId);
            ps.setLong(2, bookId);
            int status = ps.executeUpdate();
            return status == 1;
        } catch (Exception e) {
            throw new DAOException("UserBookDAOImpl createByIds() - SQL Error", e);
        }
    }
}
