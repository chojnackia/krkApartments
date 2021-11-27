package com.example.krkapartments.module.apartment;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "LOCATIONS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Location {

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
}
