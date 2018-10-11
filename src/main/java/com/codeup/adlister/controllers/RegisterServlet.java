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

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // validate input
        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (!password.equals(passwordConfirmation));

        boolean usernameIsDuplicate = DaoFactory.getUsersDao().checkUsernameDuplicates(username);

        if (inputHasErrors || usernameIsDuplicate) {
//            request.setParameter
            request.getSession().setAttribute("duplicate", usernameIsDuplicate);
//            request.getRequestDispatcher("/register").forward(request, response);
            response.sendRedirect("/register");
            return;
        } else {
            User user = new User(username, email, Password.hash(password));
            DaoFactory.getUsersDao().insert(user);
            response.sendRedirect("/login");

        }

        // hash password
        // String hash = BCrypt.hashpw(password, BCrypt.gensalt());

        // create and save a new user
//        User user = new User(username, email, Password.hash(password));

    }
}