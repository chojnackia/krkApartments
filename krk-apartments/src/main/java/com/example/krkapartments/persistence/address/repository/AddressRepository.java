package com.example.krkapartments.persistence.address.repository;

import com.example.krkapartments.persistence.address.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {

    Optional<AddressEntity> findByCityAndStreetNameAndBuildingNumberAndApartmentNumber(String city, String streetName, int buildingNumber, int apartmentNumber);

    Optional<AddressEntity> findByCity(String city);

    @Query("SELECT a FROM AddressEntity a WHERE lower(a.city) = lower(?1) AND lower(a.postCode) = lower(?2) AND lower(a.country) = lower(?3)")
    Optional<AddressEntity> findByCityAndPostCodeAndCountry(String city, String postcode, String country);

    Optional<AddressEntity> findByStreetNameAndBuildingNumberAndApartmentNumberAndCityAndCountry(String streetName, int buildingNumber, int apartmentNumber, String city, String country);

}
