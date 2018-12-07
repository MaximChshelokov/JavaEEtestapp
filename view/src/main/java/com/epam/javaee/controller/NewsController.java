package com.epam.javaee.controller;


import com.epam.javaee.service.NewsService;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestScoped
@WebServlet("/news")
public class NewsController extends HttpServlet {

    @Inject
    NewsService newsService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (newsService != null)
            req.setAttribute("newsList", newsService.getAllNews());
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
