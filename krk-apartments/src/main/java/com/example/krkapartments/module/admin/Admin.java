package com.example.krkapartments.module.admin;

import com.example.krkapartments.module.user.UserRole;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
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
