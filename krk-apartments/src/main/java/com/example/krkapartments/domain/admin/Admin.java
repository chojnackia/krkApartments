package com.example.krkapartments.domain.admin;

import com.example.krkapartments.persistence.shared.UserRole;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private UUID id;
    private String firstName;
    private String lastName;
    private UserRole role;
    private String email;
    private String password;
    private boolean active;
}
