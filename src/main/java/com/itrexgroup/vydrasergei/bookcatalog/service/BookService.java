package com.itrexgroup.vydrasergei.bookcatalog.service;

import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.BookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;

import java.util.List;

public interface BookService {

    void create(Book book) throws ServiceException;

    Book findBook(Long bookId) throws ServiceException;

    boolean editBook(Book book) throws ServiceException;

    List<Book> getAllBooks() throws ServiceException;

    void setBookDAO(BookDAO bookDAO);

    boolean remove(Long bookId) throws ServiceException;

}
