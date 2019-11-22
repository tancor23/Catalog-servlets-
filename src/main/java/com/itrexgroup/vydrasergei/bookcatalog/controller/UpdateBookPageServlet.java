package com.itrexgroup.vydrasergei.bookcatalog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update_book_page")
public class UpdateBookPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("bookId",req.getParameter("bookId"));
        req.setAttribute("bookName",req.getParameter("bookName"));
        req.setAttribute("authorName",req.getParameter("authorName"));
        req.setAttribute("countOfPage",req.getParameter("countOfPage"));
        req.getRequestDispatcher("WEB_INF/jsp/update_book_page.jsp").forward(req, resp);
    }

}

