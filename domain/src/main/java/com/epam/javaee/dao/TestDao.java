package com.epam.javaee.dao;

import com.epam.javaee.entity.News;
import com.epam.javaee.entity.NewsBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
public class TestDao implements Dao<News> {

    private Map<Long, News> base = new HashMap<>();
    private long nextId = 4L;

    {
        try {
            base.put(1L, new NewsBuilder(1, "News1", "01.02.03", "Brief1", "Content1").getNews());
            base.put(2L, new NewsBuilder(2, "News2", "02.03.04", "Brief2", "Content2").getNews());
            base.put(3L, new NewsBuilder(3, "News3", "03.04.05", "Brief3", "Content3").getNews());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean create(News news) {
        if (news == null) {
            return false;
        }
        news.setId(nextId);
        base.put(nextId++, news);
        System.out.println(nextId);
        return true;
    }

    @Override
    public News read(long id) {
        return base.get(id);
    }

    @Override
    public boolean update(News news) {
        if (news == null) {
            return false;
        }
        base.put(news.getId(), news);
        return true;
    }

    @Override
    public boolean delete(long id) {
        return base.remove(id) != null;
    }

    @Override
    public List<News> getAll() {
        return new ArrayList<>(base.values());
    }
}
