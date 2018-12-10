package com.epam.javaee.service;

import com.epam.javaee.dao.Dao;
import com.epam.javaee.entity.News;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class NewsServiceImpl implements NewsService {

    @Inject
    private Dao<News> newsDao;

    @Override
    public void addNews(News news) {
        newsDao.create(news);
    }

    @Override
    public News getNews(long id) {
        return newsDao.read(id);
    }

    @Override
    public void deleteNews(long id) {
        newsDao.delete(id);
    }

    @Override
    public void updateNews(News news) {
        newsDao.update(news);
    }

    @Override
    public List<News> getAllNews() {
        return newsDao.getAll();
    }
}
