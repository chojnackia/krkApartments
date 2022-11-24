package com.example.krkapartments.persistence.booking.entity;

import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.shared.BookingPayment;
import com.example.krkapartments.persistence.user.entity.UserEntity;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BookingEntity implements Serializable {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private UserEntity user;

    @JoinColumn(name = "apartment_id")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = ApartmentEntity.class)
    private ApartmentEntity apartment;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int price;

    @Enumerated(EnumType.STRING)
    private BookingPayment paymentStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookingEntity bookingEntity = (BookingEntity) o;

        return new EqualsBuilder().append(id, bookingEntity.id).append(user, bookingEntity.user).append(apartment, bookingEntity.apartment).append(checkInDate, bookingEntity.checkInDate).append(checkOutDate, bookingEntity.checkOutDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(user).append(apartment).append(checkInDate).append(checkOutDate).toHashCode();
    }
}
