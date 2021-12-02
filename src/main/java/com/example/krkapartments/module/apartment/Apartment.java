package com.example.krkapartments.module.apartment;

import com.example.krkapartments.module.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
    private UUID id;

    private String apartmentName;

    private double priceForOneDay;

    private String apartmentDescription;

    @OneToMany(mappedBy = "apartment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Booking> bookings;

    @OneToOne
    private Address address;

}

