package com.itrexgroup.vydrasergei.bookcatalog.service;

import com.itrexgroup.vydrasergei.bookcatalog.dao.DAOFactory;
import com.itrexgroup.vydrasergei.bookcatalog.service.impl.BookServiceImpl;
import com.itrexgroup.vydrasergei.bookcatalog.service.impl.UserBookServiceImpl;
import com.itrexgroup.vydrasergei.bookcatalog.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory instance;

    private final UserService userService;
    private final BookService bookService;
    private final UserBookService userBookService;

    private ServiceFactory() {
        DAOFactory daoFactory = DAOFactory.getInstance();

        userService = new UserServiceImpl();
        userService.setUserDAO(daoFactory.getUserDAO());

        bookService = new BookServiceImpl();
        bookService.setBookDAO(daoFactory.getBookDAO());

        userBookService = new UserBookServiceImpl();
        userBookService.setUserBookDAO(daoFactory.getUserBookDAO());
        userBookService.setUserDAO(daoFactory.getUserDAO());
        userBookService.setBookDAO(daoFactory.getBookDAO());
    }

    public static synchronized ServiceFactory getInstance() {
        if(instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public UserBookService getUserBookService() {
        return userBookService;
    }
}
