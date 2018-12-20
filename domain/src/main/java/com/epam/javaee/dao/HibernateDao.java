package com.epam.javaee.dao;

import com.epam.javaee.entity.News;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@ApplicationScoped
public class HibernateDao implements Dao<News> {

    @PersistenceContext(unitName = "news-persistence")
    private EntityManager em;

    @Override
    public boolean create(News news) {
        em.persist(news);
        return true;
    }

    @Override
    public News read(long id) {
        return em.find(News.class, id);
    }

    @Override
    public List<News> getAll() {
        return em.createNamedQuery("findAllNews", News.class).getResultList();
    }

    @Override
    public boolean update(News news) {
        News newsToUpdate = read(news.getId());
        em.getTransaction().begin();
        newsToUpdate.setTitle(news.getTitle());
        newsToUpdate.setDate(news.getDate());
        newsToUpdate.setBrief(news.getBrief());
        newsToUpdate.setContent(news.getContent());
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(long id) {
        News news = read(id);
        em.getTransaction().begin();
        em.remove(news);
        em.getTransaction().commit();
        return true;
    }
}
