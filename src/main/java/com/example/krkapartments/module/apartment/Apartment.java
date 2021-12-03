package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "APARTMENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Apartment {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "apartment_id")
    private UUID id;

    @NotBlank(message = "Apartment name is mandatory")
    private String apartmentName;

    @NotBlank(message = "Price is mandatory")
    private double priceForOneDay;

    @NotBlank(message = "Apartment Description is mandatory")
    private String apartmentDescription;

    @NotNull
    private boolean active;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;

    @OneToOne
    private Address address;

}

