package com.eomps.dao.impl;

import com.eomps.dao.ProductDAO;
import com.eomps.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Product product) {
        try {
            getSession().save(product);
            logger.info("Product saved: {}", product.getName());
        } catch (Exception e) {
            logger.error("Error saving product: {}", product.getName(), e);
            throw new IllegalArgumentException("Failed to save product");
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            // @Where already filters deleted records
            return getSession()
                    .createQuery("FROM Product", Product.class)
                    .getResultList();
        } catch (Exception e) {
            logger.error("Error fetching products", e);
            throw new IllegalArgumentException("Failed to fetch products");
        }
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Product product = getSession().get(Product.class, id);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            logger.error("Error fetching product with id: {}", id, e);
            throw new IllegalArgumentException("Failed to fetch product");
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            Product product = getSession().get(Product.class, id);

            if (product != null) {
                product.setDeleted(true); // soft delete
                getSession().update(product);
                logger.info("Product soft deleted: {}", id);
            }

        } catch (Exception e) {
            logger.error("Error deleting product: {}", id, e);
            throw new IllegalArgumentException("Failed to delete product");
        }
    }

    @Override
    public void update(Product product) {
        try {
            getSession().saveOrUpdate(product);
            logger.info("Product updated: {}", product.getId());
        } catch (Exception e) {
            logger.error("Error updating product: {}", product.getId(), e);
            throw new IllegalArgumentException("Failed to update product");
        }
    }
}