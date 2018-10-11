package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.DeletePostServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Object postId = request.getParameter("post");
        Long id = Long.parseLong(postId.toString());
        System.out.println(id);
        DaoFactory.getAdsDao().deleteAdById(id);
        response.sendRedirect("/ads");
//        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
