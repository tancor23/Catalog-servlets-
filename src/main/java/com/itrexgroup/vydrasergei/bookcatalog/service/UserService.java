package com.itrexgroup.vydrasergei.bookcatalog.service;

import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    User create(User user) throws ServiceException;

    User findUser(Long userId) throws ServiceException;

    void editUser(User user) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    void setUserDAO(UserDAO userDAO);

    boolean remove(Long userId) throws ServiceException;

}
