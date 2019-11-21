package com.itrexgroup.vydrasergei.bookcatalog.service;

import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.BookDAO;
import com.itrexgroup.vydrasergei.bookcatalog.dao.mysql.UserDAO;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;

import java.util.List;

public interface UserBookService {

    List<Book> getAllMappedBookOfUser(Long userId) throws ServiceException;

    List<User> getAllMappedUserOfBook(Long bookId) throws ServiceException;

    void setUserDAO(UserDAO userDAO);

    void setBookDAO(BookDAO bookDAO);
}
