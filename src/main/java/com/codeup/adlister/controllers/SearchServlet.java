package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "searchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String term = req.getParameter("search");

        req.setAttribute("searchTitleAds", DaoFactory.getAdsDao().searchAdsByTitle(term));
        req.setAttribute("searchDescAds", DaoFactory.getAdsDao().searchAdsByDesc(term));
        req.getRequestDispatcher("/WEB-INF/ads/search.jsp").forward(req, res);
    }
}
