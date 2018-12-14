package com.epam.javaee.commands;

import com.epam.javaee.entity.News;
import com.epam.javaee.service.NewsService;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@ApplicationScoped
public class AddNewsCommand implements Command {

    @SuppressWarnings("WeakerAccess")
    public static final String COMMAND_NAME = "edit-news";
    private static final String VIEW_REDIRECT = "redirect:/action/view-news/";

    @Inject
    NewsService newsService;
    @Inject
    Logger log;

    @Override
    public String getResponse(HttpServletRequest request) {
        switch (request.getMethod()) {
            case "GET":
                return doGet(request);
            case "POST":
                return doPost(request);
            default:
                return null;
        }
    }

    private String doGet(HttpServletRequest request) {
//        String variable = request.getPathInfo().substring(request.getPathInfo().indexOf(COMMAND_NAME) + COMMAND_NAME.length());
//        log.info("Elements: {} ", variable);
        long newsId = getPathVariable(request.getPathInfo(), COMMAND_NAME);
        if (newsId == 0)
            return JSP_ADD_NEWS;
        request.setAttribute(NEWS, newsService.getNews(newsId));
        return JSP_EDIT_NEWS;
    }

    private String doPost(HttpServletRequest request) {
        News news = getNewsEntity(request);
        log.info("Object from form data: {}", news);

        long newsId = getPathVariable(request.getPathInfo(), COMMAND_NAME);
        if (newsId == 0) {
            newsService.addNews(news);
        } else {
            news.setId(newsId);
            newsService.updateNews(news);
        }

        return VIEW_REDIRECT + news.getId();
    }

    private News getNewsEntity(HttpServletRequest request) {
        News news = new News();
        try {
            BeanUtils.populate(news, request.getParameterMap());
        } catch (Exception ex) {
            log.error("Failed to bind form data", ex);
        }
        return news;
    }
}
