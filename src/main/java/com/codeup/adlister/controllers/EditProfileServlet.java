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
        String username = request.getParameter("newUsername");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");

        boolean usernameIsDuplicate = DaoFactory.getUsersDao().checkUsernameDuplicates(username);
        System.out.println(username);
        System.out.println(usernameIsDuplicate);

        if (usernameIsDuplicate) {
            request.getSession().setAttribute("usernameDuplicate", true);
            response.sendRedirect("/profile/edit");
//            request.getRequestDispatcher("/profile/editProfile.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("usernameDuplicate", false);
        }

            if (!password.equals(rePassword)) {
                request.getSession().setAttribute("passwordMatch", false);
                response.sendRedirect("/profile/edit");
//                request.getRequestDispatcher("/profile/editProfile.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("passwordMatch", true);
            }


        request.setAttribute("usersAds", DaoFactory.getAdsDao().getAdsByUser((User) request.getSession().getAttribute("user")));
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);

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
