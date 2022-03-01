package com.example.krkapartments.consumer;

import com.example.krkapartments.module.payment.ClientTransactionRequestDTO;
import com.example.krkapartments.module.payment.ClientTransactionResponseDTO;
import com.example.krkapartments.module.user.User;
import com.example.krkapartments.module.user.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class RestConsumerImpl implements RestConsumer {

    private final RestTemplate restTemplate;

    @Value("${paymentservice.url}")
    private String paymentServiceUrl;

    private final String startTransactionUrl = "/api/transaction/request";

    public RestConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

/*//    @Override
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<ClientTransactionResponseDTO> beginTransactionForBooking(ClientTransactionRequestDTO clientTransactionRequestDTO) {
//      return new ResponseEntity<>((restTemplate.postForEntity(paymentServiceUrl + startTransactionUrl, clientTransactionRequestDTO, ClientTransactionRequestDTO.class)), HttpStatus.OK);

    }*/
}

