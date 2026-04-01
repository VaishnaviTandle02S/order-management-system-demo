package com.eomps.dao;

import com.eomps.entity.Vendor;

import java.util.Optional;

public interface VendorDAO {

    void save(Vendor vendor);

    Optional<Vendor> findByEmail(String email);
}