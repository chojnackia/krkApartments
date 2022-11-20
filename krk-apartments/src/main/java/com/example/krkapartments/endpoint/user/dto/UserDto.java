package com.example.krkapartments.endpoint.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
/*    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Booking booking;*/
}
