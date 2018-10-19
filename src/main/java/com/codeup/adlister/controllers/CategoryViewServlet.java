package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "CategoryViewServlet", urlPatterns = "/category")
public class CategoryViewServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("name");
        request.setAttribute("adsByCategory", DaoFactory.getAdsDao().getAdsByCategory(categoryName));
        request.getRequestDispatcher("/WEB-INF/ads/category.jsp").forward(request, response);
    }

}
