package com.epam.javaee.commands;

import com.epam.javaee.controller.NewsController;
import com.epam.javaee.service.NewsService;
import com.epam.javaee.service.NewsServiceLocal;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class DeleteNewsCommand implements Command {

    @SuppressWarnings("WeakerAccess")
    public static final String COMMAND_NAME = "delete-news";
    private static final String NEWS_LIST_REDIRECTION = NewsController.REDIRECTION + "/action/news-list";
    @Inject
    private NewsServiceLocal newsService;

    @Override
    public String getResponse(HttpServletRequest request) {
        long newsId = getPathVariable(request.getPathInfo(), COMMAND_NAME);
        if (newsId == 0) {
            return null;
        }
        newsService.deleteNews(newsId);
        return NEWS_LIST_REDIRECTION;
    }
}
