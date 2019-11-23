package com.itrexgroup.vydrasergei.bookcatalog.service.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
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
        List<Long> mappedBookIds;
        try {
            mappedBookIds = userDAO.getAllMappedBookIds(userId);
        } catch (DAOException e) {
            throw new ServiceException("UserBookServiceImpl getAllMappedBookOfUser(getAllMappedBookIds(userId))", e);
        }
        for (Long bookId : mappedBookIds) {
            try {
                books.add(bookDAO.findById(bookId));
            } catch (DAOException e) {
                throw new ServiceException("UserBookServiceImpl getAllMappedBookOfUser(findById(bookId))", e);
            }
        }
        return books;
    }

    @Override
    public List<User> getAllMappedUserOfBook(Long bookId) throws ServiceException {
        List<User> users = new ArrayList<>();
        List<Long> mappedUserIds;
        try {
            mappedUserIds = bookDAO.getAllMappedUserIds(bookId);
        } catch (DAOException e) {
            throw new ServiceException("UserBookServiceImpl getAllMappedUserOfBook(getAllMappedUserIds(bookId))", e);
        }
        for (Long userId : mappedUserIds) {
            try {
                users.add(userDAO.findById(userId));
            } catch (DAOException e) {
                throw new ServiceException("UserBookServiceImpl getAllMappedUserOfBook(findById(userId))", e);
            }
        }
        return users;
    }

    @Override
    public boolean createByIds(Long userId, Long bookId) throws ServiceException {
        try {
            if (userBookDAO.createByIds(userId, bookId)) {
                LOGGER.info("USER BOOK record was written");
                return true;
            } else {
                LOGGER.info("USER BOOK record was NOT written");
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException("UserBookServiceImpl createByIds()", e);
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
