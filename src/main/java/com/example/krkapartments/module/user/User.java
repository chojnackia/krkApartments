package com.example.krkapartments.module.user;

import com.example.krkapartments.module.booking.Booking;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    private String password;

    @OneToOne
    private Booking booking;
}
