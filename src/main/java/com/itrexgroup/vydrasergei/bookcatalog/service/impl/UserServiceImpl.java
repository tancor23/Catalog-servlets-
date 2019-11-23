package com.itrexgroup.vydrasergei.bookcatalog.service.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
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
    public void create(final User user) throws ServiceException {
        if (user != null) {
            try {
                if (userDAO.createUser(user.getFirstName(), user.getLastName())) {
                    LOGGER.info("UserServiceImpl create(), User was created");
                } else {
                    LOGGER.info("UserServiceImpl create(), User was NOT created");
                }
            } catch (DAOException e) {
                throw new ServiceException("UserServiceImpl create()", e);
            }
        } else {
            throw new ServiceException("UserServiceImpl create(), User object is null");
        }
    }

    @Override
    public User findUser(Long userId) throws ServiceException {
        User user;
        try {
            user = userDAO.findById(userId);
        } catch (DAOException e) {
            throw new ServiceException("UserServiceImpl findUser()", e);
        }
        return user;
    }

    @Override
    public boolean editUser(final User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException("UserServiceImpl editUser()", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("UserServiceImpl getAllUsers()", e);
        }
        return users;
    }

    @Override
    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean remove(Long userId) throws ServiceException {
        try {
            return userDAO.delete(userId);
        } catch (DAOException e) {
            throw new ServiceException("UserServiceImpl remove()");
        }
    }

    public boolean remove(final User user) throws ServiceException {
        return remove(user.getId());
    }

}
