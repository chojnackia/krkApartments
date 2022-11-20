package com.example.krkapartments.endpoint.payment.dto;

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
