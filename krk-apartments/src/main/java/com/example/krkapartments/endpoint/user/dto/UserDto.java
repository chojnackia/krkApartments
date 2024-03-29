package com.example.krkapartments.endpoint.user.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
}
