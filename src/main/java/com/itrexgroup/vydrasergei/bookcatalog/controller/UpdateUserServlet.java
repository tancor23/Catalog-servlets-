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

@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        long id = Long.parseLong(req.getParameter("userId"));
        User user = new User(req.getParameter("firstName"), req.getParameter("lastName"));
        user.setId(id);
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            userService.editUser(user);
        } catch (ServiceException e) {
            throw new ServletException("UpdateUserServlet doPost()", e);
        }
        req.getRequestDispatcher("/start").forward(req, resp);
    }

}

