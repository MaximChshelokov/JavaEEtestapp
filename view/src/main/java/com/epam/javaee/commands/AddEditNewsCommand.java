package com.epam.javaee.commands;

import com.epam.javaee.entity.News;
import com.epam.javaee.service.NewsService;
import com.epam.javaee.util.ConstraintsTranslator;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;

@ApplicationScoped
public class AddEditNewsCommand implements Command {

    @SuppressWarnings("WeakerAccess")
    public static final String COMMAND_NAME = "edit-news";
    private static final String VIEW_REDIRECT = "redirect:/action/view-news/";

    @Inject
    NewsService newsService;
    @Inject
    Logger log;
    @Inject
    ConstraintsTranslator constraintsTranslator;

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
        long newsId = getPathVariable(request.getPathInfo(), COMMAND_NAME);
        if (newsId == 0) {
            return JSP_ADD_NEWS;
        }
        if (request.getAttribute(NEWS) == null) {
            request.setAttribute(NEWS, newsService.getNews(newsId));
        }
        return JSP_EDIT_NEWS;
    }

    private String doPost(HttpServletRequest request) {
        News news = getNewsEntity(request);
        log.info("Object from form data: {}", news);

        Map violations = constraintsTranslator.translate(news);
        log.info("Constraint violations: {}", violations);
        long newsId = getPathVariable(request.getPathInfo(), COMMAND_NAME);

        if (!violations.isEmpty()) {
            request.setAttribute("errors", violations);
            request.setAttribute(NEWS, news);
            return doGet(request);
        }

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
