package com.example.krkapartments.module.checkout;

import lombok.Data;

@Data
public class StripeChargeRequestDto {
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    public enum Currency {
        EUR, USD, PLN;
    }
}
