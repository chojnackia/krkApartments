package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.booking.Booking;
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
public class Apartment implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @NotBlank(message = "Apartment name is mandatory")
    private String apartmentName;

    @NotBlank(message = "Price is mandatory")
    private double priceForOneDay;

    @NotBlank(message = "Apartment Description is mandatory")
    private String apartmentDescription;

    @NotNull
    @JsonIgnore
    private boolean active;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    @OneToOne
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Apartment apartment = (Apartment) o;

        return new EqualsBuilder().append(priceForOneDay, apartment.priceForOneDay).append(active, apartment.active).append(id, apartment.id).append(apartmentName, apartment.apartmentName).append(apartmentDescription, apartment.apartmentDescription).append(bookings, apartment.bookings).append(address, apartment.address).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(apartmentName).append(priceForOneDay).append(apartmentDescription).append(active).append(bookings).append(address).toHashCode();
    }
}

