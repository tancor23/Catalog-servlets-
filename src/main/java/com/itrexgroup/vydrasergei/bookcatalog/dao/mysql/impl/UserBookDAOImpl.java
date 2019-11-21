package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserBookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.UserBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserBookDAOImpl extends UserBookDAO {
    private static final String ADD_NEW_USER_BOOK_SQL = "INSERT INTO user_book (`user_id`, `book_id`) VALUES (?, ?);";
    private static final String UPDATE_USER_BOOK_SQL = "UPDATE user_book SET user_id=?, book_id=? WHERE id=?;";
    private static final String GET_USER_BOOK_BY_ID_SQL = "SELECT id, user_id, book_id FROM user_book WHERE id=?;";
    private static final String GET_ALL_USER_BOOK_SQL = "SELECT * FROM user_book;";
    private static final String DELETE_USER_BOOK_BY_ID_SQL = "DELETE FROM user_book WHERE id=?;";


    public UserBookDAOImpl(Datasource datasource) {
        super(datasource);
    }

    @Override
    public UserBook create(UserBook userBook) throws DAOException {
        PreparedStatement statement = null;
        int status;
        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(ADD_NEW_USER_BOOK_SQL);
            statement.setLong(1, userBook.getUserId());
            statement.setLong(2, userBook.getBookId());

            status = statement.executeUpdate();
            if (status != 1) {
                return userBook;
            }
        } catch (Exception e) {
            throw new DAOException("UserBook create() - SQL Error", e);
        }
        return userBook;
    }

    @Override
    public UserBook findById(Long id) throws DAOException {
        PreparedStatement prStatement = null;
        ResultSet rs = null;
        UserBook userBook = new UserBook();
        userBook.setId(id);

        try (Connection connection = datasource.getConnection()) {
            prStatement = connection.prepareStatement(GET_USER_BOOK_BY_ID_SQL);
            prStatement.setLong(1, id);
            rs = prStatement.executeQuery();

            if (rs.next()) {
                userBook.setId(rs.getLong(1));
                userBook.setId(rs.getLong(2));
                userBook.setId(rs.getLong(3));
            } else {
                userBook = null;
            }
        } catch (Exception e) {
            throw new DAOException("UserBook findById() - SQL Error", e);
        }
        return userBook;
    }

    @Override
    public List<UserBook> findAll() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<UserBook> userBooks = new ArrayList<>();

        try (Connection connection = datasource.getConnection()) {
            ps = connection.prepareStatement(GET_ALL_USER_BOOK_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                UserBook userBook = new UserBook();
                userBook.setId(rs.getInt(1));
                userBook.setUserId(rs.getInt(2));
                userBook.setBookId(rs.getInt(3));
                userBooks.add(userBook);
            }
        } catch (Exception e) {
            throw new DAOException("book findAll() - SQL Error", e);
        }

        return userBooks;
    }

    @Override
    public UserBook update(UserBook userBook) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(UPDATE_USER_BOOK_SQL);
            statement.setLong(1, userBook.getUserId());
            statement.setLong(2, userBook.getBookId());
            statement.setLong(3, userBook.getId());

            status = statement.executeUpdate();
            if (status != 1) {
                userBook = null;
            }
        } catch (Exception e) {
            throw new DAOException("UserBook update() - SQL Error", e);
        }
        return userBook;
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(DELETE_USER_BOOK_BY_ID_SQL);
            statement.setLong(1, id);
            status = statement.executeUpdate();
            if (status == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new DAOException("UserBook update() - SQL Error", e);
        }
        return false;
    }

    @Override
    public UserBook create(UserBook entity, Connection connection) throws DAOException {
        return null;
    }
}
