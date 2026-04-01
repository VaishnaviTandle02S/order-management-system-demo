package com.eomps.service.impl;

import com.eomps.dao.VendorDAO;
import com.eomps.entity.Vendor;
import com.eomps.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

    private static final Logger logger = LoggerFactory.getLogger(VendorServiceImpl.class);

    private final VendorDAO vendorDAO;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public VendorServiceImpl(VendorDAO vendorDAO,
                             BCryptPasswordEncoder encoder) {
        this.vendorDAO = vendorDAO;
        this.encoder = encoder;
    }

    // 🔹 Register Vendor
    @Override
    public void register(Vendor vendor) {

        if (vendor.getEmail() == null || vendor.getEmail().trim().isEmpty()
                || vendor.getPassword() == null || vendor.getPassword().trim().isEmpty()) {

            throw new IllegalArgumentException("Email and Password cannot be empty");
        }

        vendorDAO.findByEmail(vendor.getEmail())
                .ifPresent(v -> {
                    throw new IllegalArgumentException("Vendor already exists");
                });

        // 🔐 Encrypt password
        vendor.setPassword(encoder.encode(vendor.getPassword()));

        vendorDAO.save(vendor);

        logger.info("Vendor registered: {}", vendor.getEmail());
    }

    // 🔹 Login Vendor
    @Override
    public Vendor login(String email, String password) {

        Vendor vendor = vendorDAO.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));

        if (!encoder.matches(password, vendor.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        logger.info("Vendor logged in: {}", email);

        return vendor;
    }
}