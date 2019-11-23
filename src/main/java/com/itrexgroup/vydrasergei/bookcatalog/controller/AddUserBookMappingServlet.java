package com.itrexgroup.vydrasergei.bookcatalog.controller;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.BookService;
import com.itrexgroup.vydrasergei.bookcatalog.service.ServiceFactory;
import com.itrexgroup.vydrasergei.bookcatalog.service.UserBookService;
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

@WebServlet("/user_book/add_mapping")
public class AddUserBookMappingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", prepareAllUsers());
        req.setAttribute("books", prepareAllBooks());
        req.getRequestDispatcher("/WEB_INF/jsp/add_user_book_mapping.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(req.getParameter("userSelect"));
        long bookId = Long.parseLong(req.getParameter("bookSelect"));
        UserBookService userBookService = ServiceFactory.getInstance().getUserBookService();
        try {
            userBookService.createByIds(userId, bookId);
            req.setAttribute("alert", "Successful");
        } catch (ServiceException e) {
            req.setAttribute("alert", "This mapping is already in DB");
        }
        doGet(req,resp);
    }

    private List<User> prepareAllUsers() throws ServletException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users;
        try {
            users = userService.getAllUsers();
        } catch (ServiceException e) {
            throw new ServletException("AddUserBookMappingServlet prepareAllUsers()", e);
        }
        return users;
    }

    private List<Book> prepareAllBooks() throws ServletException {
        BookService userService = ServiceFactory.getInstance().getBookService();
        List<Book> books;
        try {
            books = userService.getAllBooks();
        } catch (ServiceException e) {
            throw new ServletException("AddUserBookMappingServlet prepareAllBooks()", e);
        }
        return books;
    }

}
