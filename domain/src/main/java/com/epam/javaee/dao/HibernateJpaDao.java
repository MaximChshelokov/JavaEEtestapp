package com.epam.javaee.dao;

import com.epam.javaee.entity.News;
import org.slf4j.Logger;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Named
@ApplicationScoped
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class HibernateJpaDao implements Dao<News> {

    @PersistenceContext(unitName = "news-persistence")
    private EntityManager entityManager;
    @Inject
    private UserTransaction userTransaction;
    @Inject
    private Logger log;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean create(News news) {
        try {
            userTransaction.begin();
            entityManager.persist(news);
            entityManager.flush();
            userTransaction.commit();
        } catch(Exception ex) {
            log.error("Cannot create entity: {}", news);
            return false;
        }
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean update(News news) {
        try {
            userTransaction.begin();
            News newsToUpdate = read(news.getId());
            newsToUpdate.setTitle(news.getTitle());
            newsToUpdate.setDate(news.getDate());
            newsToUpdate.setBrief(news.getBrief());
            newsToUpdate.setContent(news.getContent());
            entityManager.flush();
            userTransaction.commit();
        } catch(Exception ex) {
            log.error("Cannot update entity: {}", news);
            return false;
        }
        return true;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean delete(long id) {
        try {
            userTransaction.begin();
            News news = read(id);
            entityManager.remove(news);
            entityManager.flush();
            userTransaction.commit();
        } catch(Exception ex) {
            log.error("Cannot delete entity with id: {}", id);
            return false;
        }
        return true;
    }
}
