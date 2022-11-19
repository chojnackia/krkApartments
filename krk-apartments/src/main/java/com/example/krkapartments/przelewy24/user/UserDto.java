package com.example.krkapartments.przelewy24.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String telephoneNumber;
/*    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Booking booking;*/
}
