package com.example.krkapartments.module.address;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findAllByCityAndStreetNameAndBuildingNumberAndApartmentNumber(String city, String streetName, int buildingNumber, int apartmentNumber);

    Optional<Address> findByCity(String city);
}
