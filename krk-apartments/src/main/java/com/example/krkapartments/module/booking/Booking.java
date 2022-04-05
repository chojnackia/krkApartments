package com.example.krkapartments.module.booking;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.user.User;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "BOOKINGS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    private int price;

    @Enumerated(EnumType.STRING)
    private BookingPayment paymentStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        return new EqualsBuilder().append(id, booking.id).append(user, booking.user).append(apartment, booking.apartment).append(checkInDate, booking.checkInDate).append(checkOutDate, booking.checkOutDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(user).append(apartment).append(checkInDate).append(checkOutDate).toHashCode();
    }
}
