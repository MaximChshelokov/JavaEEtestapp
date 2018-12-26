package com.epam.javaee.soap;

import com.epam.javaee.entity.News;
import com.epam.javaee.service.NewsServiceLocal;

import java.util.List;
import javax.inject.Inject;
import javax.jws.WebService;

@WebService(endpointInterface = "com.epam.javaee.soap.NewsInterface",
    serviceName = "News")
public class NewsServiceEndpoint implements NewsInterface {

    @Inject
    private NewsServiceLocal newsService;

    @Override
    public boolean add(News news) {
        newsService.addNews(news);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        newsService.deleteNews(id);
        return false;
    }

    @Override
    public boolean update(News news) {
        newsService.updateNews(news);
        return false;
    }

    @Override
    public News get(Long id) {
        return newsService.getNews(id);
    }

    @Override
    public List<News> getAll() {
        return newsService.getAllNews();
    }
}
