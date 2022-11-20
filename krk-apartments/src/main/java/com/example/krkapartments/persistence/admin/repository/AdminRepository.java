package com.example.krkapartments.persistence.admin.repository;

import com.example.krkapartments.persistence.admin.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<AdminEntity, UUID> {
    Optional<AdminEntity> findByEmailAllIgnoreCase(String email);

    Optional<AdminEntity> findByPassword(String password);
}
