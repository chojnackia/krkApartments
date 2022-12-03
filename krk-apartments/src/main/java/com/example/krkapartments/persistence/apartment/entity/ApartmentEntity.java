package com.example.krkapartments.persistence.apartment.entity;

import com.example.krkapartments.persistence.address.entity.AddressEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "apartment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class ApartmentEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String apartmentName;

    private int priceForOneDay;

    private String apartmentDescription;

    @NotNull
    @JsonIgnore
    private boolean active = true;

    private String bookingUrl;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private AddressEntity address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApartmentEntity apartmentEntity = (ApartmentEntity) o;

        return new EqualsBuilder().append(priceForOneDay, apartmentEntity.priceForOneDay).append(active, apartmentEntity.active).append(id, apartmentEntity.id).append(apartmentName, apartmentEntity.apartmentName).append(apartmentDescription, apartmentEntity.apartmentDescription).append(bookingUrl, apartmentEntity.bookingUrl).append(address, apartmentEntity.address).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(apartmentName).append(priceForOneDay).append(apartmentDescription).append(active).append(bookingUrl).append(address).toHashCode();
    }
}

