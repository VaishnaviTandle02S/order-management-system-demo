package com.eomps.service;

import com.eomps.entity.User;

public interface UserService {

    void register(User user);

    User login(String username, String password);

    boolean isUsernameAvailable(String username);  // ⭐ for UI validation
}