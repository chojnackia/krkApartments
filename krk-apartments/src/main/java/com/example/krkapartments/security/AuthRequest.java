package com.example.krkapartments.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AuthRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
