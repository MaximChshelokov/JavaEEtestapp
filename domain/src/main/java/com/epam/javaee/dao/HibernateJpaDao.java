package com.epam.javaee.dao;

import com.epam.javaee.entity.News;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class HibernateJpaDao implements DaoLocal<News>, DaoRemote<News> {

    @PersistenceContext(unitName = "news-persistence")
    private EntityManager entityManager;

    @Override
    @Transactional
    public boolean create(News news) {
        entityManager.persist(news);
        entityManager.flush();

        return true;
    }

    @Override
    public News read(long id) {
        return entityManager.find(News.class, id);
    }

    @Override
    public List<News> getAll() {
        return entityManager.createNamedQuery("findAllNews", News.class).getResultList();
    }

    @Override
    @Transactional
    public boolean update(News news) {

        News newsToUpdate = read(news.getId());
        newsToUpdate.setTitle(news.getTitle());
        newsToUpdate.setDate(news.getDate());
        newsToUpdate.setBrief(news.getBrief());
        newsToUpdate.setContent(news.getContent());
        entityManager.flush();
        return true;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        News news = read(id);
        entityManager.remove(news);
        entityManager.flush();
        return true;
    }
}
