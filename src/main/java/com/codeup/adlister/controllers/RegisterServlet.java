package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: show the registration form
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: ensure the submitted information is valid

        // TODO: create a new user based off of the submitted information
        // TODO: if a user was successfully created, send them to their profile
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
//        Long userId = DaoFactory.getUserDao().insert(user);
//                1,
//                request.getParameter("username"),
//                request.getParameter("email"),
//                request.getParameter("password")
//        );
        try {
            DaoFactory.getUserDao().insert(user);

        } catch (SQLException e) {
            System.out.println("Could not insert new user into the database");
            System.out.println(e);
        }
//        DaoFactory.getUserDao().insert((request.getParameter("username"), request.getParameter("email"), request.getParameter("password")));
        response.sendRedirect("/profile");
    }
}
