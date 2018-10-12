package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.updateServlet", urlPatterns = "/update")
public class UpdateServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String adId = request.getParameter("post");
        request.getSession().setAttribute("updatedAdId", adId);
        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String newTitle = request.getParameter("newTitle");
        System.out.println("New Title: " + newTitle);
        String newDescription = request.getParameter("newDescription");
        System.out.println("New Desc: " + newDescription);
        Object adId = request.getParameter("post");
        System.out.println("AD ID: " + adId);

        DaoFactory.getAdsDao().updatePost(newTitle, newDescription, Long.parseLong(adId.toString()));
        response.sendRedirect("/ads");
    }
}
