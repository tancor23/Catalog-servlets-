package com.itrexgroup.vydrasergei.bookcatalog.service.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.BookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserBookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.UserBookService;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserBookServiceImpl implements UserBookService {
    private final static Logger LOGGER = LogManager.getLogger(UserBookServiceImpl.class);
    private UserDAO userDAO;
    private BookDAO bookDAO;
    private UserBookDAO userBookDAO;

    @Override
    public List<Book> getAllMappedBookOfUser(Long userId) throws ServiceException {
        List<Book> books = new ArrayList<>();
        List<Long> mappedBookIds = null;
        try {
            mappedBookIds = userDAO.getAllMappedBookIds(userId);
        } catch (DAOException e) {
            LOGGER.error("userDAO.getAllMappedBookIds(), DAOException exception");
            throw new ServiceException("userDAO.getAllMappedBookIds(), DAOException exception");
        }
        for (Long bookId : mappedBookIds) {
            try {
                books.add(bookDAO.findById(bookId));
            } catch (DAOException e) {
                LOGGER.error("bookDAO.findById(bookId), DAOException exception");
                throw new ServiceException("bookDAO.findById(bookId), DAOException exception");
            }
        }
        return books;
    }

    @Override
    public List<User> getAllMappedUserOfBook(Long bookId) throws ServiceException {
        List<User> users = new ArrayList<>();
        List<Long> mappedUserIds = null;
        try {
            mappedUserIds = bookDAO.getAllMappedUserIds(bookId);
        } catch (DAOException e) {
            LOGGER.error("bookDAO.getAllMappedUserIds(), DAOException exception");
            throw new ServiceException("bookDAO.getAllMappedUserIds(), DAOException exception");
        }
        for (Long userId : mappedUserIds) {
            try {
                users.add(userDAO.findById(userId));
            } catch (DAOException e) {
                LOGGER.error("userDAO.findById(userId), DAOException exception");
                throw new ServiceException("userDAO.findById(userId), DAOException exception");
            }
        }
        return users;
    }

    @Override
    public boolean createByIds(Long userId, Long bookId) throws ServiceException {
        try {
            if(userBookDAO.createByIds(userId, bookId)){
                LOGGER.info("USER BOOK record was written");
                return true;
            }else{
                LOGGER.warn("USER BOOK record was NOT written");
                return false;
            }
        } catch (DAOException e) {
            LOGGER.error("userBookDAO.createByIds(), DAOException exception");
            throw new ServiceException("userBookDAO.createByIds(), DAOException exception");
        }
    }

    @Override
    public void setUserBookDAO(UserBookDAO userBookDAO) {
        this.userBookDAO = userBookDAO;
    }

    @Override
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
}
