package com.example.krkapartments.przelewy24.service.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
@Getter
@Setter
public class ReturnTransactionRequest {

    @NonNull
    private int merchantId;
    @NonNull
    private int posId;
    @NonNull
    private String sessionId;
    @NonNull
    private int amount;
    @NonNull
    private int originAmount;
    @NonNull
    private String currency;
    @NonNull
    private int orderId;
    @NonNull
    private int methodId;
    @NonNull
    private String statement;
    @NonNull
    private String sign;
}
