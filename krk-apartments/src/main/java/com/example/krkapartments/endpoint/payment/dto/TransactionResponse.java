package com.example.krkapartments.endpoint.payment.dto;

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