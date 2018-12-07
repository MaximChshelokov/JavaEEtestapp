package com.epam.javaee.service;

import com.epam.javaee.entity.News;

import java.util.List;

public interface NewsService {

    void addNews(News news);

    News getNews(long id);

    void deleteNews(long id);

    void updateNews(News news);

    List<News> getAllNews();
}
