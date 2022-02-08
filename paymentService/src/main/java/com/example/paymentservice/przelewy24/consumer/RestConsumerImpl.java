package com.example.paymentservice.przelewy24.consumer;

import com.example.paymentservice.przelewy24.user.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestConsumerImpl implements RestConsumer {

    private final RestTemplate restTemplate;

    @Value("${krkApartments.url}")
    private String paymentServiceUrl;

    private final String postUserUrl = "users/";

    public RestConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendDataForUserCreation(UserDto userDto) {
        restTemplate.postForObject(paymentServiceUrl + postUserUrl,userDto ,UserDto.class);
    }

}

