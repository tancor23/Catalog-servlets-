package com.itrexgroup.vydrasergei.bookcatalog.controller;

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

@WebServlet("/book/delete")
public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long id  = Long.parseLong(req.getParameter("bookId"));
        BookService bookService = ServiceFactory.getInstance().getBookService();
        try {
            bookService.remove(id);
        } catch (ServiceException e) {
            throw new ServletException("DeleteBookServlet doPost(bookService.remove())", e);
        }
        req.getRequestDispatcher("/start").forward(req, resp);
    }

}

