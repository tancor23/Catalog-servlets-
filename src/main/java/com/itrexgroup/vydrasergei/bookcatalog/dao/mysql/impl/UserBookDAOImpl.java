package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserBookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.UserBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    public void update(UserBook entity) throws DAOException {

    }

    @Override
    public boolean delete(Long id) throws DAOException {
        return false;
    }

    @Override
    public boolean createByIds(Long userId, Long bookId) throws DAOException {
        PreparedStatement ps = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            ps = connection.prepareStatement(ADD_USER_BOOK_BY_IDS_SQL);

            ps.setLong(1, userId);
            ps.setLong(2, bookId);

            status = ps.executeUpdate();
            if (status != 1) {
                return false;
            }
        } catch (Exception e) {
            throw new DAOException("UserBookDAOImpl createByIds() - SQL Error", e);
        }
        return true;
    }

}
