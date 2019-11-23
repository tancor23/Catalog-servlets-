package com.itrexgroup.vydrasergei.bookcatalog.service.impl;

import com.itrexgroup.vydrasergei.bookcatalog.dao.exception.DAOException;
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
    public void create(final Book book) throws ServiceException {
        if (book != null) {
            try {
                if (bookDAO.createBook(book.getName(), book.getAuthor(), book.getPage())) {
                    LOGGER.info("BookServiceImpl, book was created");
                } else {
                    LOGGER.info("BookServiceImpl, book was not created");
                }
            } catch (DAOException e) {
                throw new ServiceException("BookServiceImpl create()", e);
            }
        } else {
            throw new ServiceException("BookServiceImpl create(), Book object is null");
        }
    }

    @Override
    public Book findBook(Long bookId) throws ServiceException {
        Book book;
        try {
            book = bookDAO.findById(bookId);
        } catch (DAOException e) {
            throw new ServiceException("BookServiceImpl findBook(), Book was not found", e);
        }
        return book;
    }

    @Override
    public boolean editBook(final Book book) throws ServiceException {
        try {
            return bookDAO.update(book);
        } catch (DAOException e) {
            throw new ServiceException("BookServiceImpl editBook(), Book was not updated", e);
        }
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        List<Book> books;
        try {
            books = bookDAO.findAll();
        } catch (DAOException e) {
            throw new ServiceException("BookServiceImpl getAllBooks(), Books were not selected from DB", e);
        }
        return books;
    }

    public void setBookDAO(final BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean remove(Long bookId) throws ServiceException {
        try {
            return bookDAO.delete(bookId);
        } catch (DAOException e) {
            throw new ServiceException("BookServiceImpl remove(), Books were not removed from DB", e);
        }
    }

    public boolean remove(final Book book) throws ServiceException {
        return remove(book.getId());
    }

}
