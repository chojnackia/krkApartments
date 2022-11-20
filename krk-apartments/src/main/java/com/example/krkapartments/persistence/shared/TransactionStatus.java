package com.example.krkapartments.persistence.shared;

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
