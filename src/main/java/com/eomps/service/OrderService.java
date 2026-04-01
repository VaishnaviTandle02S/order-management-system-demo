package com.eomps.service;

import com.eomps.entity.Order;

import java.util.List;

public interface OrderService {

    void placeOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getUserOrders(Long userId);

    List<Order> getAllOrders();
}