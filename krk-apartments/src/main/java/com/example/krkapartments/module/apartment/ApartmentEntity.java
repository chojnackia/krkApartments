package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.AddressEntity;
import com.example.krkapartments.module.booking.BookingEntity;
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
@Table(name = "APARTMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ApartmentEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Apartment name is mandatory")
    private String apartmentName;

    @NotBlank(message = "Price is mandatory")
    private int priceForOneDay;

    @NotBlank(message = "Apartment Description is mandatory")
    private String apartmentDescription;

    @NotNull
    @JsonIgnore
    private boolean active;

    private String bookingUrl;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<BookingEntity> bookings = new ArrayList<>();

    @OneToOne
    private AddressEntity address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApartmentEntity apartmentEntity = (ApartmentEntity) o;

        return new EqualsBuilder().append(priceForOneDay, apartmentEntity.priceForOneDay).append(active, apartmentEntity.active).append(id, apartmentEntity.id).append(apartmentName, apartmentEntity.apartmentName).append(apartmentDescription, apartmentEntity.apartmentDescription).append(bookingUrl, apartmentEntity.bookingUrl).append(bookings, apartmentEntity.bookings).append(address, apartmentEntity.address).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(apartmentName).append(priceForOneDay).append(apartmentDescription).append(active).append(bookingUrl).append(bookings).append(address).toHashCode();
    }
}

