package com.eomps.service;

import com.eomps.entity.Product;
import com.eomps.entity.User;

import java.util.List;

public interface ProductService {

    void addProduct(Product product);

    List<Product> getAllProducts();

    void deleteProduct(Long id);

    Product getProductById(Long id);

    void updateProduct(Product product);

    void buyProduct(Long productId, User user);
}