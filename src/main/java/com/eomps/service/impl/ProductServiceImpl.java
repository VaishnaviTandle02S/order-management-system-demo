package com.eomps.service.impl;

import com.eomps.dao.OrderDAO;
import com.eomps.dao.ProductDAO;
import com.eomps.entity.Order;
import com.eomps.entity.Product;
import com.eomps.entity.User;
import com.eomps.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO, OrderDAO orderDAO) {
        this.productDAO = productDAO;
        this.orderDAO = orderDAO;
    }

    // 🔹 Add Product
    @Override
    public void addProduct(Product product) {

        validateProduct(product);

        productDAO.save(product);

        logger.info("Product added: {}", product.getName());
    }

    // 🔹 Get All Products
    @Override
    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    // 🔹 Delete Product
    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteById(id);
        logger.info("Product deleted (soft): {}", id);
    }

    // 🔹 Get Product By ID
    @Override
    public Product getProductById(Long id) {

        return productDAO.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Product not found: {}", id);
                    return new RuntimeException("Product not found");
                });
    }

    // 🔹 Update Product
    @Override
    public void updateProduct(Product product) {

        Product existing = getProductById(product.getId());

        existing.setName(product.getName());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());

        productDAO.update(existing);

        logger.info("Product updated: {}", product.getId());
    }

    // 🔥 Buy Product (IMPORTANT FLOW)
    @Override
    public void buyProduct(Long productId, User user) {

        if (user == null) {
            throw new IllegalArgumentException("User not logged in");
        }

        Product product = getProductById(productId);

        Order order = new Order();
        order.setProduct(product);
        order.setUser(user);
        order.setQuantity(1);
        order.setTotalPrice(product.getPrice());
        order.setOrderDate(LocalDateTime.now());

        orderDAO.save(order);

        logger.info("Order placed: User={} Product={}", user.getUsername(), product.getName());
    }

    // 🔹 Validation method
    private void validateProduct(Product product) {

        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
}