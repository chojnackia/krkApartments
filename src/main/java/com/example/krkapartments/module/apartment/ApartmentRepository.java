package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ApartmentRepository extends JpaRepository<Apartment, UUID> {

    Optional<Apartment> findByApartmentNameIgnoreCase(String name);

    @Query("SELECT a FROM Apartment a LEFT JOIN FETCH a.address WHERE a.active = ?1")
    List<Apartment> findAllByActive(boolean active);

    Optional<Apartment> findByApartmentNameAndAddress(String name, Address address);

}
