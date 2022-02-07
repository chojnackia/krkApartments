package com.example.paymentservice.przelewy24.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private TransactionToken data;
    private int responseCode;

    @Setter
    @Getter
    @AllArgsConstructor
    public static class TransactionToken {
        private String token;
    }
}