package com.eomps.dao;

import com.eomps.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDAO {

    void save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    void update(Product product);
}