package com.example.krkapartments.endpoint.payment.dto;

import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClientTransactionRequestDTO {
    private int amount;
    private String currency;
    private String description;
    private String email;
    private String client;
    private String phone;
}
