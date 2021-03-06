package com.itrexgroup.vydrasergei.bookcatalog.controller;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.BookService;
import com.itrexgroup.vydrasergei.bookcatalog.service.ServiceFactory;
import com.itrexgroup.vydrasergei.bookcatalog.service.UserService;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/start")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", prepareAllUsers());
        req.setAttribute("books", prepareAllBooks());
        req.getRequestDispatcher("/WEB_INF/jsp/main_page.jsp").forward(req, resp);
    }

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    private List<User> prepareAllUsers() throws ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users;
        try {
            users = userService.getAllUsers();
        } catch (ServiceException e) {
            throw new ServletException("MainServlet prepareAllUsers(userService.remove())", e);
        }
        return users;
    }

    private List<Book> prepareAllBooks() throws ServletException {
        BookService userService = ServiceFactory.getInstance().getBookService();
        List<Book> books;
        try {
            books = userService.getAllBooks();
        } catch (ServiceException e) {
            throw new ServletException("MainServlet prepareAllBooks(userService.getAllBooks())", e);
        }
        return books;
    }

}
