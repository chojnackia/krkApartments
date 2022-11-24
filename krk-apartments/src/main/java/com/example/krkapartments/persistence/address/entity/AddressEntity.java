package com.example.krkapartments.persistence.address.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressEntity implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity addressEntity = (AddressEntity) o;

        return new EqualsBuilder().append(buildingNumber, addressEntity.buildingNumber).append(apartmentNumber, addressEntity.apartmentNumber).append(id, addressEntity.id).append(city, addressEntity.city).append(streetName, addressEntity.streetName).append(postCode, addressEntity.postCode).append(country, addressEntity.country).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(city).append(streetName).append(buildingNumber).append(apartmentNumber).append(postCode).append(country).toHashCode();
    }
}
