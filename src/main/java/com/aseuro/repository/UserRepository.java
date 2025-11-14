package com.aseuro.repository;

import com.aseuro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByName(String name);

    boolean existsByEmail(String email);
}