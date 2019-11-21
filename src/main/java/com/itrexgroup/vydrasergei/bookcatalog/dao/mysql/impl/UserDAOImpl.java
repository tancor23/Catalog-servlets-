package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
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
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(CHECK_USER_BY_ID_SQL);
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            rs = statement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
            }

        } catch (Exception e) {
            throw new DAOException("getUserInDB() - SQL Error", e);
        }

        return user;
    }

    @Override
    public boolean createUser(String firstName, String lastName) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(ADD_NEW_USER_SQL);

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            status = statement.executeUpdate();
            if (status != 1) {
                return false;
            }
        } catch (Exception e) {
            throw new DAOException("createUser() - SQL Error", e);
        }
        return true;
    }

    @Override
    public List<Long> getAllMappedBookIds(Long userId) throws DAOException {
        List<Long> ids = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try (Connection connection = datasource.getConnection()) {
            ps = connection.prepareStatement(SELECT_ALL_MAPPED_BOOK_ID_SQL);
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getLong(1));
            }
        } catch (Exception e) {
            throw new DAOException("UserDAOImpl getAllMappedUserIds() - SQL Error", e);
        }
        return ids;
    }

    @Override
    public User create(User user) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(ADD_NEW_USER_SQL);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());

            status = statement.executeUpdate();
            if (status != 1) {
                return user;
            }
        } catch (Exception e) {
            throw new DAOException("createUser() - SQL Error", e);
        }
        return user;
    }


    @Override
    public User findById(Long id) throws DAOException {
        PreparedStatement prStatement = null;
        ResultSet rs = null;
        User user = new User();
        user.setId(id);

        try (Connection connection = datasource.getConnection()) {
            prStatement = connection.prepareStatement(GET_USER_BY_ID_SQL);
            prStatement.setLong(1, id);
            rs = prStatement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
            } else {
                user = null;
            }
        } catch (Exception e) {
            throw new DAOException("findById() - SQL Error", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try (Connection connection = datasource.getConnection()) {
            ps = connection.prepareStatement(GET_ALL_USERS_SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
                users.add(user);
            }
        } catch (Exception e) {
            throw new DAOException("findAll() - SQL Error", e);
        }

        return users;
    }

    @Override
    public void update(User user) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setLong(3, user.getId());

            status = statement.executeUpdate();
            if (status != 1) {
                user = null;
            }
        } catch (Exception e) {
            throw new DAOException("update() - SQL Error", e);
        }
    }

    @Override
    public boolean delete(Long id) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(DELETE_USER_BY_ID_SQL);
            statement.setLong(1, id);
            status = statement.executeUpdate();
            if (status == 1) {
                return true;
            }
        } catch (Exception e) {
            throw new DAOException("update() - SQL Error", e);
        }
        return false;
    }

}
