package com.epam.javaee.commands;

import com.epam.javaee.service.NewsServiceLocal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class NewsListCommand implements Command {

    @SuppressWarnings("WeakerAccess")
    public static final String COMMAND_NAME = "news-list";

    @Inject
    private NewsServiceLocal newsService;

    @Override
    public String getResponse(HttpServletRequest request) {
        request.setAttribute("newsList", newsService.getAllNews());
        return JSP_NEWS_LIST;
    }
}
