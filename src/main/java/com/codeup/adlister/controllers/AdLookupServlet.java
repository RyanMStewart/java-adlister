package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.AdLookupServlet", urlPatterns = "/view")
public class AdLookupServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        System.out.println(id);
        String creator = request.getParameter("user");
        User currentUser = (User) request.getSession().getAttribute("user");
        Ad ad = DaoFactory.getAdsDao().getAdById(Long.parseLong(id));
        User adCreator = DaoFactory.getUsersDao().getUserById(Long.parseLong(creator));

        if (currentUser == null) {
            //            Ad ad = DaoFactory.getAdsDao().getAdById(Long.parseLong(id));
            //            User adCreator = DaoFactory.getUsersDao().getUserById(Long.parseLong(creator));
            request.setAttribute("ad", ad);
            request.setAttribute("creator", adCreator);
            request.getRequestDispatcher("/WEB-INF/ads/adInfo.jsp").forward(request, response);
            return;
        }

        if (ad.getUserId() == currentUser.getId()) {
            request.getSession().setAttribute("loggedInCreator", true);
        } else {
            request.getSession().setAttribute("loggedInCreator", false);
        }

        request.setAttribute("ad", ad);
        request.setAttribute("creator", adCreator);
        request.getRequestDispatcher("WEB-INF/ads/adInfo.jsp").forward(request, response);
    }
}