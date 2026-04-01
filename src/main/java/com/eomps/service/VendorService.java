package com.eomps.service;

import com.eomps.entity.Vendor;

public interface VendorService {

    void register(Vendor vendor);

    Vendor login(String email, String password);
}