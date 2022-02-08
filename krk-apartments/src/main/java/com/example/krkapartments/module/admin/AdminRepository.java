package com.example.krkapartments.module.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Optional<Admin> findByEmailAllIgnoreCase(String email);
    Optional<Admin> findByPassword(String password);
}
