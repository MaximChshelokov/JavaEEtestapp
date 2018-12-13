package com.epam.javaee.commands;

import com.epam.javaee.service.NewsService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class ViewNewsCommand implements Command {
    public static final String COMMAND_NAME = "view-news";
    @Inject
    private NewsService newsService;

    @Override
    public String getResponse(HttpServletRequest request) {
        long newsId = getPathVariable(request.getPathInfo(), COMMAND_NAME);
        if (newsId == 0)
            return null;
        request.setAttribute(NEWS, newsService.getNews(newsId));
        return JSP_NEWS_VIEW;
    }
}
