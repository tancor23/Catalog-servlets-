package com.itrexgroup.vydrasergei.bookcatalog.service.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.UserService;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final static Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private UserDAO userDAO;

    @Override
    public boolean isUserExists(String firstName, String lastName) throws ServiceException {
        boolean isLoggedIn;
        try {
            LOGGER.info("Checking login matches");
            isLoggedIn = userDAO.getUserInDB(firstName, lastName) != null;
        } catch (DAOException e) {
            LOGGER.warn("User exist checking error: {}", e);
            throw new ServiceException(e);
        }
        return isLoggedIn;
    }

    @Override
    public User create(User user) throws ServiceException {
        if (user != null) {
            try {
                LOGGER.info("Created user in UserServiceImpl");
                if (userDAO.createUser(user.getFirstName(), user.getLastName())) {
                    user = userDAO.getUserInDB(user.getFirstName(), user.getLastName());
                } else {
                    LOGGER.warn("User was not created in DB");
                }
            } catch (DAOException e) {
                LOGGER.error("User was not created, DAOException exception");
                throw new ServiceException("User object is null");
            }
        } else {
            LOGGER.error("User object is null");
            throw new ServiceException("User object is null");
        }
        return user;
    }

    @Override
    public User findUser(Long userId) throws ServiceException {
        User user = null;
        try {
            user = userDAO.findById(userId);
        } catch (DAOException e) {
            LOGGER.error("User was not found, DAOException exception");
            throw new ServiceException("findUser(), DAOException exception");
        }
        return user;
    }

    @Override
    public User editUser(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            LOGGER.error("User was not updated, DAOException exception");
            throw new ServiceException("editUser(), DAOException exception");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users = null;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            LOGGER.error("Users were not selected from DB, DAOException exception");
            throw new ServiceException("getAllUsers(), DAOException exception");
        }
        return users;
    }


    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean remove(Long userId) throws ServiceException {
        boolean isUserDeleted = false;
        try {
            isUserDeleted = userDAO.delete(userId);
        } catch (DAOException e) {
            LOGGER.error("Users were not removed from DB, DAOException exception");
            throw new ServiceException("remove(), DAOException exception");
        }
        return isUserDeleted;
    }

    public boolean remove(User user) throws ServiceException {
        return remove(user.getId());
    }

}
