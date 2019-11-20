package com.itrexgroup.vydrasergei.bookcatalog.service;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOFactory;
import com.itrexgroup.vydrasergei.bookcatalog.service.impl.BookServiceImpl;
import com.itrexgroup.vydrasergei.bookcatalog.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;

    private final UserService userService;
    private final BookService bookService;

    private ServiceFactory() {
        DAOFactory daoFactory = DAOFactory.getInstance();

        userService = new UserServiceImpl();
        userService.setUserDAO(daoFactory.getUserDAO());

        bookService = new BookServiceImpl();
        bookService.setBookDAO(daoFactory.getBookDAO());
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }
}
