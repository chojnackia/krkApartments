package com.example.paymentservice.przelewy24.data.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    CREATED("CREATED"),
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    String status;

    TransactionStatus(String status) {
        this.status = status;
    }

}
