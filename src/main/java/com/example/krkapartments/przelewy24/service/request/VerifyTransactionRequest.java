package com.example.krkapartments.przelewy24.service.request;

import com.example.krkapartments.przelewy24.data.entity.Transaction;
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

    public VerifyTransactionRequest(Transaction transaction) {
        merchantId = transaction.getMerchantId();
        posId = transaction.getPosId();
        sessionId = transaction.getSessionId();
        amount = transaction.getAmount();
        currency = transaction.getCurrency();
        orderId = transaction.getOrderId();
    }
}
