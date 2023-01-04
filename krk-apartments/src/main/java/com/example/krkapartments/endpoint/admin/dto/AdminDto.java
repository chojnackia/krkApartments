package com.example.krkapartments.endpoint.admin.dto;

import com.example.krkapartments.persistence.shared.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String email;
    @ToString.Exclude
    private String password;
    private boolean active;
}
