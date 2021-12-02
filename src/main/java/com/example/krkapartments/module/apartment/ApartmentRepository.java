package com.example.krkapartments.module.apartment;

import liquibase.pro.packaged.O;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

    Optional<Apartment> findByApartmentNameIgnoreCase(String name);
    Optional<Apartment> findAllByActive(boolean active);


}
