package com.eomps.dao.impl;

import com.eomps.dao.OrderDAO;
import com.eomps.entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    private static final Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Order order) {
        try {
            getSession().save(order);
            logger.info("Order saved for user: {}", order.getUser().getUsername());
        } catch (Exception e) {
            logger.error("Error saving order", e);
            throw new IllegalArgumentException("Failed to save order");
        }
    }

    @Override
    public Order findById(Long id) {
        try {
            return getSession().createQuery(
                            "SELECT o FROM Order o " +
                                    "JOIN FETCH o.user " +
                                    "JOIN FETCH o.product " +
                                    "WHERE o.id = :id",
                            Order.class)
                    .setParameter("id", id)
                    .uniqueResult();
        } catch (Exception e) {
            logger.error("Error fetching order: {}", id, e);
            throw new IllegalArgumentException("Failed to fetch order");
        }
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        try {
            return getSession().createQuery(
                            "SELECT o FROM Order o " +
                                    "JOIN FETCH o.user " +
                                    "JOIN FETCH o.product " +
                                    "WHERE o.user.id = :uid",
                            Order.class)
                    .setParameter("uid", userId)
                    .getResultList();

        } catch (Exception e) {
            logger.error("Error fetching orders for user: {}", userId, e);
            throw new IllegalArgumentException("Failed to fetch orders");
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            return getSession().createQuery(
                            "SELECT o FROM Order o " +
                                    "JOIN FETCH o.user " +
                                    "JOIN FETCH o.product",
                            Order.class)
                    .getResultList();

        } catch (Exception e) {
            logger.error("Error fetching all orders", e);
            throw new IllegalArgumentException("Failed to fetch orders");
        }
    }
}