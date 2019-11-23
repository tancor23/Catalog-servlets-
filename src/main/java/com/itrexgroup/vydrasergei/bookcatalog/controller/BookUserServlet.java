package com.itrexgroup.vydrasergei.bookcatalog.controller;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.Book;
import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
import com.itrexgroup.vydrasergei.bookcatalog.service.BookService;
import com.itrexgroup.vydrasergei.bookcatalog.service.ServiceFactory;
import com.itrexgroup.vydrasergei.bookcatalog.service.UserBookService;
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

@WebServlet("/book_user")
public class BookUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserBookService userBookService = ServiceFactory.getInstance().getUserBookService();
        BookService bookService = ServiceFactory.getInstance().getBookService();
        req.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("bookId"));
        Book book;
        try {
            book = bookService.findBook(id);
        } catch (ServiceException e) {
            throw new ServletException("BookUserServlet doPost(bookService.findBook())", e);
        }
        List<User> allMappedUserOfBook;
        try {
            allMappedUserOfBook = userBookService.getAllMappedUserOfBook(id);
        } catch (ServiceException e) {
            throw new ServletException("BookUserServlet doPost(userBookService.getAllMappedUserOfBook())", e);
        }
        List<String> userNames = getFullNameOfUser(allMappedUserOfBook);

        req.setAttribute("bookName", book.getName());
        req.setAttribute("bookUserSize", userNames.size());
        req.setAttribute("bookUsers", userNames);
        req.getRequestDispatcher("/WEB_INF/jsp/book_user_page.jsp").forward(req, resp);
    }

    private List<String> getFullNameOfUser(List<User> users){
        return users.stream().map(User::getFullName).collect(Collectors.toList());
    }

}

