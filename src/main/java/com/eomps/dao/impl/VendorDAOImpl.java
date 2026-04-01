package com.eomps.dao.impl;

import com.eomps.dao.VendorDAO;
import com.eomps.entity.Vendor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Repository
public class VendorDAOImpl implements VendorDAO {

    private static final Logger logger = LoggerFactory.getLogger(VendorDAOImpl.class);

    private final SessionFactory sessionFactory;

    @Autowired
    public VendorDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Vendor vendor) {
        try {
            getSession().saveOrUpdate(vendor);
            logger.info("Vendor saved: {}", vendor.getEmail());
        } catch (Exception e) {
            logger.error("Error saving vendor", e);
            throw new IllegalArgumentException("Failed to save vendor");
        }
    }

    @Override
    public Optional<Vendor> findByEmail(String email) {
        try {
            Vendor vendor = getSession()
                    .createQuery("FROM Vendor WHERE email = :email", Vendor.class)
                    .setParameter("email", email)
                    .uniqueResult();

            return Optional.ofNullable(vendor);

        } catch (Exception e) {
            logger.error("Error fetching vendor: {}", email, e);
            throw new IllegalArgumentException("Failed to fetch vendor");
        }
    }
}