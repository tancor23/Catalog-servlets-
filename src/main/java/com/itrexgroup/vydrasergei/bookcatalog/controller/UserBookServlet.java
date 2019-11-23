package com.itrexgroup.vydrasergei.bookcatalog.controller;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
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
import java.util.stream.Collectors;

@WebServlet("/user_book")
public class UserBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBookService userBookService = ServiceFactory.getInstance().getUserBookService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        long id = Long.parseLong(req.getParameter("userId"));
        User user;
        try {
            user = userService.findUser(id);
        } catch (ServiceException e) {
            throw new ServletException("UserBookServlet doPost(userService.findUser())", e);
        }
        List<Book> allMappedBookOfUser;
        try {
            allMappedBookOfUser = userBookService.getAllMappedBookOfUser(id);
        } catch (ServiceException e) {
            throw new ServletException("UserBookServlet doPost(userBookService.getAllMappedBookOfUser())", e);
        }
        List<String> nameOfBooks = getNameOfBooks(allMappedBookOfUser);
        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("lastName", user.getLastName());
        req.setAttribute("userBookNames", nameOfBooks);
        req.setAttribute("userBookNameSize", nameOfBooks.size());
        req.getRequestDispatcher("/WEB_INF/jsp/user_book_page.jsp").forward(req, resp);
    }

    private List<String> getNameOfBooks(List<Book> books){
        return books.stream().map(Book::getName).collect(Collectors.toList());
    }

}

