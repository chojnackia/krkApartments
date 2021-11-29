package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "BOOKINGS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Booking {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Apartment apartment;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private boolean occupied;

}
