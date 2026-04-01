package com.eomps.dao;

import com.eomps.entity.Order;
import java.util.List;

public interface OrderDAO {

    void save(Order order);

    Order findById(Long id);

    List<Order> findByUserId(Long userId);

    List<Order> findAll();
}