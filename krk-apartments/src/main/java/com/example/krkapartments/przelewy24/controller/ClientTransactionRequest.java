package com.example.krkapartments.przelewy24.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@Getter
@Setter
public class ClientTransactionRequest {
    @NonNull
    private int amount;
    @NonNull
    private String currency;
    @NonNull
    private String description;
    @NonNull
    private String email;
    @NonNull
    private String client;
    private String phone;
}
