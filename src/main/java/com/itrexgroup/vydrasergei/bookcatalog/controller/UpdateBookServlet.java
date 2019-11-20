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

@WebServlet("/update_book")
public class UpdateBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("bookId"));
        Book book = new Book(req.getParameter("bookName"), req.getParameter("authorName"), Integer.parseInt(req.getParameter("countOfPage")));
        BookService bookService = ServiceFactory.getInstance().getBookService();
        book.setId(bookId);
        try {
            bookService.editBook(book);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/start").forward(req, resp);
    }

}

