package com.example.krkapartments.module.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {

    Optional<Address> findByCityAndStreetNameAndBuildingNumberAndApartmentNumber(String city, String streetName, int buildingNumber, int apartmentNumber);

    Optional<Address> findByCity(String city);

    @Query("SELECT a FROM Address a WHERE lower(a.city) = lower(?1) AND lower(a.postCode) = lower(?2) AND lower(a.country) = lower(?3)")
    Optional<Address> findByCityAndPostCodeAndCountry(String city, String postcode, String country);

    Optional<Address> findByStreetNameAndBuildingNumberAndApartmentNumberAndCityAndCountry(String streetName, int buildingNumber, int apartmentNumber, String city, String country);

}
