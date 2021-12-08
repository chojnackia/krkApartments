package com.example.krkapartments.module.user;

import com.example.krkapartments.module.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    private String telephoneNumber;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Booking> bookings;
}
