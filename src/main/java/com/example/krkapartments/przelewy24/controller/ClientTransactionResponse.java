package com.example.krkapartments.przelewy24.controller;

import com.example.krkapartments.przelewy24.service.request.RegisterTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@Getter
@Setter
public class ClientTransactionResponse {
    @NonNull
    private RegisterTransactionRequest request;
    @NonNull
    private String token;
}
