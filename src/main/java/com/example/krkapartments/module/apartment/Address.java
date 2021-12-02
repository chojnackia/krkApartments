package com.example.krkapartments.module.apartment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    private UUID id;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Post code is mandatory")
    private String postCode;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Apartment apartment;
}
