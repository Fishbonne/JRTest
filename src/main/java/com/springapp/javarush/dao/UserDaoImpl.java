package com.springapp.javarush.dao;

import com.springapp.javarush.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User successfully updated. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));

        if (user != null) {
            session.delete(user);
        }
        logger.info("User successfully deleted. User details: " + user);
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        logger.info("User successfully loaded. User details: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> list = session.createQuery("from User").list();

        for (User user : list) {
            logger.info("User list: " + user);
        }
        return list;
    }

    @Override
    public List<User> getUserBySearch(String searchString) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", "%" + searchString + "%"));
        List<User> list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public Long getUserCount() {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setProjection(Projections.rowCount());
        Long count = (Long) criteria.uniqueResult();
        session.close();
        return count;
    }

    @Override
    public List<User> getUserListPage(int page) {
        Session session = this.sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.setFirstResult((page - 1) * 5);
        criteria.setMaxResults(5);
        List<User> list = criteria.list();
        session.close();
        return list;

    }


}
