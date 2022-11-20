package com.example.krkapartments.module.user;

import com.example.krkapartments.module.booking.BookingEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
public class UserEntity {

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
    private List<BookingEntity> bookings;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserEntity userEntity = (UserEntity) o;

        return new EqualsBuilder().append(id, userEntity.id).append(firstName, userEntity.firstName).append(lastName, userEntity.lastName).append(email, userEntity.email).append(telephoneNumber, userEntity.telephoneNumber).append(bookings, userEntity.bookings).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(firstName).append(lastName).append(email).append(telephoneNumber).append(bookings).toHashCode();
    }
}
