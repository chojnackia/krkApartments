package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "BOOKINGS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Booking implements Serializable {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JoinColumn
    @ManyToOne
    private User user;

    @JoinColumn(name = "apartment_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Apartment.class)
    private Apartment apartment;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @ElementCollection
    @CollectionTable(name = "apartment_is_occupied",
    joinColumns = {@JoinColumn(name = "apartment_id", referencedColumnName = "apartment_id")})
    @MapKeyColumn(name = "date")
    private Map<LocalDate, Boolean> isOccupied;

}
