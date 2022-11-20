package com.example.krkapartments.endpoint.payment.dto;

import com.example.krkapartments.persistence.payment.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerifyTransactionRequest {

    private int merchantId;
    private int posId;
    private String sessionId;
    private int amount;
    private String currency;
    private Integer orderId;
    private String sign;

    public VerifyTransactionRequest(TransactionEntity transactionEntity) {
        merchantId = transactionEntity.getMerchantId();
        posId = transactionEntity.getPosId();
        sessionId = transactionEntity.getSessionId();
        amount = transactionEntity.getAmount();
        currency = transactionEntity.getCurrency();
        orderId = transactionEntity.getOrderId();
    }
}
