package com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.dbconfig.Datasource;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserDAOImpl extends UserDAO {
    private static final String CHECK_USER_BY_ID_SQL = "SELECT id,first_name,last_name,created_at FROM user WHERE first_name=? and last_name=?;";
    private static final String ADD_NEW_USER_SQL = "INSERT INTO user (`first_name`, `last_name`) VALUES (?, ?);";
    private static final String UPDATE_USER_SQL = "UPDATE user SET first_name=?, last_name=? WHERE id=?;";
    private static final String GET_USER_BY_ID_SQL = "SELECT * FROM user WHERE id=?;";

    public UserDAOImpl(Datasource datasource) {
        super(datasource);
    }

    @Override
    public User isUserInDB(String first_name, long last_name) throws DAOException {
        PreparedStatement statement = null;
        ResultSet rs = null;
        User user = null;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(CHECK_USER_BY_ID_SQL);
            statement.setString(1, first_name);
            statement.setLong(2, last_name);

            rs = statement.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setCreatedAt(rs.getString(4));
            }

        } catch (Exception e) {
            throw new DAOException("isUserInDB() - SQL Error", e);
        }

        return user;
    }

    @Override
    public boolean createUser(User user, long hashPassword) throws DAOException {
        PreparedStatement statement = null;
        int status;

        try (Connection connection = datasource.getConnection()) {
            statement = connection.prepareStatement(ADD_NEW_USER_SQL);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());

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
    public User create(User entity, Connection connection) throws DAOException {
        return null;
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
                user = new User();
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
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User user) throws DAOException {
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
        return user;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

}
