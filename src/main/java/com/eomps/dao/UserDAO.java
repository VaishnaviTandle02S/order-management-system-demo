package com.eomps.dao;

import com.eomps.entity.User;
import java.util.Optional;

public interface UserDAO {

    void save(User user);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);   // ⭐ important for validation
}