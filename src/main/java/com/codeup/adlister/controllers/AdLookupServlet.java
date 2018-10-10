package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

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
        String creator = request.getParameter("user");
//        System.out.println(id);
        request.setAttribute("ad", DaoFactory.getAdsDao().getAdById(Long.parseLong(id)));
        request.setAttribute("creator", DaoFactory.getUsersDao().getUserById(Long.parseLong(creator)));
        request.getRequestDispatcher("WEB-INF/ads/adInfo.jsp").forward(request, response);
    }

}
