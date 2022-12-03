package com.example.krkapartments.persistence.address.entity;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class AddressEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String city;

    private String streetName;

    private int buildingNumber;

    private int apartmentNumber;

    private String postCode;

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
