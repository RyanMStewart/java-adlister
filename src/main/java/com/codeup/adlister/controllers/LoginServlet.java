package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().setAttribute("stickyUsername", username);
        request.getSession().setAttribute("stickyPassword", password);
        User user = DaoFactory.getUsersDao().findByUsername(username);

        if (user == null) {
            response.sendRedirect("/login");
            return;
        }
        try {
            boolean validAttempt = BCrypt.checkpw(password, user.getPassword());
//        boolean validAttempt = password.equals(user.getPassword());
            if (validAttempt) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("/profile");
            } else {
                    request.getSession().setAttribute("failedLogin", true);
                    response.sendRedirect("/login");
//                        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
//                    } catch (ServletException e) {
//                        System.out.println("Could not forward request");
//                        throw new RuntimeException("This forward failed.");
//                    }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("This password is in plain text. It was never hashed and cannot be compared for login");
        }


    }
}
