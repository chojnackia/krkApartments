package com.example.krkapartments.endpoint.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ClientTransactionResponseDTO {
    private ClientTransactionRequestDTO request;
    private String token;
}
