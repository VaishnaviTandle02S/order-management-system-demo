package com.eomps.dao.impl;

import com.eomps.dao.UserDAO;
import com.eomps.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(User user) {
        try {
            getSession().saveOrUpdate(user);
            logger.info("User saved successfully: {}", user.getUsername());
        } catch (Exception e) {
            logger.error("Error saving user: {}", user.getUsername(), e);
            throw new IllegalArgumentException("Failed to save user");
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            User user = getSession()
                    .createQuery("FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();

            return Optional.ofNullable(user);

        } catch (Exception e) {
            logger.error("Error fetching user: {}", username, e);
            throw new IllegalArgumentException("Failed to fetch user");
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        try {
            Long count = getSession()
                    .createQuery("SELECT COUNT(u.id) FROM User u WHERE u.username = :username", Long.class)
                    .setParameter("username", username)
                    .uniqueResult();

            return count != null && count > 0;

        } catch (Exception e) {
            logger.error("Error checking username existence: {}", username, e);
            throw new IllegalArgumentException("Error checking username");
        }
    }
}