package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "EditProfileServlet", urlPatterns = "/profile/edit")
public class EditProfileServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute("user") ==  null) {
            response.sendRedirect("/login");
        } else {
            request.getRequestDispatcher("/WEB-INF/ads/editProfile.jsp").forward(request, response);
        }

    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String currentUsername = request.getParameter("oldUsername");
        String username = request.getParameter("newUsername");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        System.out.println(password);

        User currentUser = (User) request.getSession().getAttribute("user");

        boolean passwordIsValid = DaoFactory.getUsersDao().checkPasswordRequirements(password);
        boolean usernameIsDuplicate = DaoFactory.getUsersDao().checkUsernameDuplicates(username);
        boolean oldUsernameIsGood = currentUser.getUsername().equals(currentUsername);
        boolean passwordsMatch = password.equals(rePassword);

            if(currentUsername == null || currentUsername.equals("")) {
                request.getSession().setAttribute("usernameIsNull", true);
                response.sendRedirect("/profile/edit");
                return;
            } else {
                request.getSession().setAttribute("usernameIsNull", false);
            }

            if(!currentUser.getUsername().equals(currentUsername)) {
                request.getSession().setAttribute("oldUsernameMatches", false);
                response.sendRedirect("/profile/edit");
                return;
            } else {
                request.getSession().setAttribute("oldUsernameMatches", true);
            }

            if (usernameIsDuplicate) {
                request.getSession().setAttribute("usernameDuplicate", true);
                response.sendRedirect("/profile/edit");
                return;
            } else {
                request.getSession().setAttribute("usernameDuplicate", false);
            }

            if (!password.equals(rePassword)) {
                request.getSession().setAttribute("passwordMatch", false);
                response.sendRedirect("/profile/edit");
                return;
            } else {
                request.getSession().setAttribute("passwordMatch", true);
            }

            if(!passwordIsValid) {
                request.getSession().setAttribute("passwordIsValid", false);
                response.sendRedirect("/profile/edit");
                return;
            } else {
                request.getSession().setAttribute("passwordIsValid", true);
            }

        if (oldUsernameIsGood && !usernameIsDuplicate && passwordsMatch && passwordIsValid) {
            DaoFactory.getUsersDao().updateUsername(username, currentUser);
            System.out.println("The password going into update is: " + password);
            User updatedUser = DaoFactory.getUsersDao().findByUsername(username);
            DaoFactory.getUsersDao().updatePassword(password, updatedUser);


            // UPDATE CURRENT USER IN SESSION SCOPE AFTER PROFILE UPDATE
            User user = DaoFactory.getUsersDao().findByUsername(username);
            request.getSession().setAttribute("user", user);

            request.setAttribute("usersAds", DaoFactory.getAdsDao().getAdsByUser((User) request.getSession().getAttribute("user")));
            response.sendRedirect("/profile");
        }


//            else {
//                request.getSession().setAttribute("passwordMatch", );
//            }
//        if ((boolean) request.getSession().getAttribute("usernameDuplicate") || (boolean) request.getSession().getAttribute("passwordMatch"))  {
//                response.sendRedirect("/profile/edit");
//            } else {
//                response.sendRedirect("/profile");
//            }

//            request.getRequestDispatcher("/profile").forward(request, response);
//            else  {
//
////                DaoFactory.getUsersDao().updateUsername(username);
//                request.getRequestDispatcher("/profile").forward(request, response);
//            }

    }
}
