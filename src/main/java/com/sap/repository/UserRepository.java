package com.sap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
