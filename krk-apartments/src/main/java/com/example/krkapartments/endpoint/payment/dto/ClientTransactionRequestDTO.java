package com.example.krkapartments.endpoint.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
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
