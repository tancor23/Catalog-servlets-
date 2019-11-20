package com.itrexgroup.vydrasergei.bookcatalog.service.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOException;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.BookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.service.BookService;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final static Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);
    private BookDAO bookDAO;

    @Override
    public boolean isBookExists(String name, String author) throws ServiceException {
        boolean isLoggedIn;
        try {
            isLoggedIn = bookDAO.getBookInDB(name, author) != null;
        } catch (DAOException e) {
            LOGGER.warn("Book exist checking, error", e);
            throw new ServiceException(e);
        }
        return isLoggedIn;
    }

    @Override
    public Book create(Book book) throws ServiceException {
        if (book != null) {
            try {
                LOGGER.info("Created book in BookServiceImpl");
                if (bookDAO.createBook(book.getName(), book.getAuthor(), book.getPage())) {
                    book = bookDAO.getBookInDB(book.getName(), book.getAuthor());
                } else {
                    LOGGER.warn("Book was not created in DB");
                }
            } catch (DAOException e) {
                LOGGER.error("Book was not created, DAOException exception");
                throw new ServiceException("Book object is null");
            }
        } else {
            LOGGER.error("Book object is null");
            throw new ServiceException("Book object is null");
        }
        return book;
    }

    @Override
    public Book findBook(Long bookId) throws ServiceException {
        Book book = null;
        try {
            book = bookDAO.findById(bookId);
        } catch (DAOException e) {
            LOGGER.error("Book was not found, DAOException exception");
            throw new ServiceException("findBook(), DAOException exception");
        }
        return book;
    }

    @Override
    public Book editBook(Book book) throws ServiceException {
        try {
            bookDAO.update(book);
        } catch (DAOException e) {
            LOGGER.error("Book was not updated, DAOException exception");
            throw new ServiceException("editBook(), DAOException exception");
        }
        return book;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        List<Book> books = null;
        try {
            books = bookDAO.findAll();
        } catch (DAOException e) {
            LOGGER.error("Books were not selected from DB, DAOException exception");
            throw new ServiceException("getAllBooks(), DAOException exception");
        }
        return books;
    }

    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean remove(Long bookId) throws ServiceException {
        boolean isBookDeleted = false;
        try {
            isBookDeleted = bookDAO.delete(bookId);
        } catch (DAOException e) {
            LOGGER.error("Books were not removed from DB, DAOException exception");
            throw new ServiceException("book remove(), DAOException exception");
        }
        return isBookDeleted;
    }

    public boolean remove(Book book) throws ServiceException {
        return remove(book.getId());
    }

}
