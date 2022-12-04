package com.example.krkapartments.endpoint.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@Getter
@Setter
public class UserCreateCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
}
