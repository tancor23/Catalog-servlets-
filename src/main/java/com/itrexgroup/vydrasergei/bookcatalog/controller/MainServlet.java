package com.itrexgroup.vydrasergei.bookcatalog.controller;

import com.itrexgroup.vydrasergei.bookcatalog.domain.entity.User;
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
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/start")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet method()");

        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users = new ArrayList<>();
        try {
            users = userService.getAllUsers();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("users",users);
        req.getRequestDispatcher("WEB_INF/jsp/main_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
