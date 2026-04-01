package com.eomps.service.impl;

import com.eomps.dao.OrderDAO;
import com.eomps.entity.Order;
import com.eomps.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final String ORDER_QUEUE = "order.queue"; // ✅ constant

    private final OrderDAO orderDAO;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public OrderServiceImpl(OrderDAO orderDAO, JmsTemplate jmsTemplate) {
        this.orderDAO = orderDAO;
        this.jmsTemplate = jmsTemplate;
    }

    // 🔥 ASYNC ORDER PROCESSING
    @Override
    public void placeOrder(Order order) {

        validateOrder(order);

        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());

        // 🔥 SEND TO JMS QUEUE
        jmsTemplate.convertAndSend(ORDER_QUEUE, order);

        logger.info("Order sent to queue for async processing");
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDAO.findById(id);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderDAO.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    // 🔹 Validation
    private void validateOrder(Order order) {

        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getUser() == null) {
            throw new IllegalArgumentException("User is required");
        }

        if (order.getProduct() == null) {
            throw new IllegalArgumentException("Product is required");
        }

        if (order.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
}