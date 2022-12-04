package com.example.krkapartments.persistence.apartment.repository;

import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<ApartmentEntity, UUID> {

    @Query("SELECT a FROM ApartmentEntity a LEFT JOIN FETCH a.address WHERE a.active = ?1")
    List<ApartmentEntity> findAllByActive(boolean active);
}
