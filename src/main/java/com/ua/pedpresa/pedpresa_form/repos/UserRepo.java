package com.ua.pedpresa.pedpresa_form.repos;

import com.ua.pedpresa.pedpresa_form.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}