package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Check that the a user is logged in before allowed to create ads
        if (request.getSession().getAttribute("user") == null) {
            // response.sendRedirect("/login");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
            // If user is logged in forward them to CreateAd page
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
            .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

            // Find the user that is making the post
//        String username = request.getParameter("username");
//      User adCreator = DaoFactory.getUsersDao().findByUsername(user.toString());

            // Cast returned 'Object' to 'User'
        User user = (User) request.getSession().getAttribute("user") ;
        System.out.println(user.getClass());

            // Set the ID dynamically on the posted Ad
        Ad ad = new Ad(
            user.getId(),
            request.getParameter("title"),
            request.getParameter("description")
        );
            // Add the ad to the database
        DaoFactory.getAdsDao().insert(ad);

            // Redirect to the Ads page
        response.sendRedirect("/ads");
    }
}
