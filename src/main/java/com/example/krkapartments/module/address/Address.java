package com.example.krkapartments.module.address;

import com.example.krkapartments.module.apartment.Apartment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private UUID id;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Street name is mandatory")
    private String streetName;

    @NotBlank(message = "Building number is mandatory")
    private int buildingNumber;

    @NotBlank(message = "Apartment number is mandatory")
    private int apartmentNumber;

    @NotBlank(message = "Post code is mandatory")
    private String postCode;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Apartment apartment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return new EqualsBuilder().append(buildingNumber, address.buildingNumber).append(apartmentNumber, address.apartmentNumber).append(id, address.id).append(city, address.city).append(streetName, address.streetName).append(postCode, address.postCode).append(country, address.country).append(apartment, address.apartment).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(city).append(streetName).append(buildingNumber).append(apartmentNumber).append(postCode).append(country).append(apartment).toHashCode();
    }
}
