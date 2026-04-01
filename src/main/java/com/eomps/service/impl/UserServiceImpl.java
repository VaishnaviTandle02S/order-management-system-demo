package com.eomps.service.impl;

import com.eomps.dao.UserDAO;
import com.eomps.entity.User;
import com.eomps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder encoder) {
        this.userDAO = userDAO;
        this.encoder = encoder;
    }

    // 🔹 Register User
    @Override
    public void register(User user) {

        validateUser(user);

        // ✅ Duplicate check using optimized DAO method
        if (userDAO.existsByUsername(user.getUsername())) {
            logger.warn("Registration failed: User already exists -> {}", user.getUsername());
            throw new IllegalArgumentException("Username already taken");
        }

        // 🔐 Encrypt password
        user.setPassword(encoder.encode(user.getPassword()));

        // 💾 Save user
        userDAO.save(user);

        logger.info("User registered successfully: {}", user.getUsername());
    }

    // 🔹 Login User
    @Override
    public User login(String username, String password) {

        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warn("Login failed: User not found -> {}", username);
                    return new IllegalArgumentException("Invalid username or password");
                });

        // ✅ Password check
        if (!encoder.matches(password, user.getPassword())) {
            logger.warn("Login failed: Incorrect password -> {}", username);
            throw new IllegalArgumentException("Invalid username or password");
        }

        logger.info("User logged in successfully: {}", username);

        return user;
    }

    // 🔹 Username availability check
    @Override
    public boolean isUsernameAvailable(String username) {
        return !userDAO.existsByUsername(username);
    }

    // 🔹 Validation logic (clean separation)
    private void validateUser(User user) {

        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER"); // default role
        }
    }
}