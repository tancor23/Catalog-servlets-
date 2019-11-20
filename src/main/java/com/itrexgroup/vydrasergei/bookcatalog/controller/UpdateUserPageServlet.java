package com.itrexgroup.vydrasergei.bookcatalog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update_user_page")
public class UpdateUserPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("userId",req.getParameter("id"));
        req.getRequestDispatcher("WEB_INF/jsp/update_user_page.jsp").forward(req, resp);
    }

}

