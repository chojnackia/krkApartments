package com.example.krkapartments.module.admin;

import com.example.krkapartments.module.user.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean active;
}
