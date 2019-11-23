package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends UserDAO {
    private static final String CHECK_USER_BY_ID_SQL = "SELECT id,first_name,last_name,created_at FROM users WHERE first_name=? and last_name=?;";
    private static final String ADD_NEW_USER_SQL = "INSERT INTO users (`first_name`, `last_name`) VALUES (?, ?);";
    private static final String UPDATE_USER_SQL = "UPDATE users SET first_name=?, last_name=? WHERE id=?;";
    private static final String GET_USER_BY_ID_SQL = "SELECT * FROM users WHERE id=?;";
    private static final String GET_ALL_USERS_SQL = "SELECT * FROM users;";
    private static final String DELETE_USER_BY_ID_SQL = "DELETE FROM users WHERE id=?;";
    private static final String SELECT_ALL_MAPPED_BOOK_ID_SQL = "SELECT book_id FROM user_book WHERE user_id=?;";

    public UserDAOImpl(Datasource datasource) {
        super(datasource);
    }

    @Override
    public User getUserInDB(String firstName, String lastName) throws DAOException {
        User user = null;
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(CHECK_USER_BY_ID_SQL)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl getUserInDB() - SQL Error", e);
        }
        return user;
    }

    @Override
    public boolean createUser(String firstName, String lastName) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(ADD_NEW_USER_SQL)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            int status = ps.executeUpdate();
            if (status != 1) {
                return false;
            }
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl createUser() - SQL Error", e);
        }
        return true;
    }

    @Override
    public List<Long> getAllMappedBookIds(Long userId) throws DAOException {
        List<Long> ids = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_MAPPED_BOOK_ID_SQL)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getLong(1));
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl getAllMappedUserIds() - SQL Error", e);
        }
        return ids;
    }

    @Override
    public User create(final User user) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(ADD_NEW_USER_SQL)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            int status = ps.executeUpdate();
            if (status != 1) {
                return user;
            }
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl createUser() - SQL Error", e);
        }
        return user;
    }

    @Override
    public User findById(Long id) throws DAOException {
        User user = new User();
        user.setId(id);

        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID_SQL)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
            } else {
                user = null;
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl findById() - SQL Error", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(GET_ALL_USERS_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
                users.add(user);
            }
            rs.close();
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl findAll() - SQL Error", e);
        }
        return users;
    }

    @Override
    public boolean update(final User user) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SQL)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setLong(3, user.getId());
            int status = ps.executeUpdate();
            return status == 1;
        } catch (Exception e) {
            throw new DAOException("update() - SQL Error", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        try (Connection connection = datasource.getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            ps.setLong(1, id);
            int status = ps.executeUpdate();
            return status == 1;
        } catch (Exception e) {
            throw new DAOException("update() - SQL Error", e);
        }
    }
}
