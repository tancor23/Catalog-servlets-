package com.itrexgroup.vydrasergei.bookcatalog.controller;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.service.BookService;
import com.itrexgroup.vydrasergei.bookcatalog.service.ServiceFactory;
import com.itrexgroup.vydrasergei.bookcatalog.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book/create")
public class CreateBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int page;
        try {
            page = Integer.parseInt(req.getParameter("countOfPage"));
        }catch (NumberFormatException e){
            page = 1;
        }
        Book book = new Book(req.getParameter("bookName"), req.getParameter("authorName"), page);
        BookService bookService = ServiceFactory.getInstance().getBookService();
        try {
            bookService.create(book);
        } catch (ServiceException e) {
            throw new ServletException("CreateBookServlet doPost(bookService.create())", e);
        }
        req.getRequestDispatcher("/start").forward(req, resp);
    }

}
