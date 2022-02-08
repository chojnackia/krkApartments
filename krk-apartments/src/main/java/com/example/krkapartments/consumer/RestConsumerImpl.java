package com.example.krkapartments.consumer;

import com.example.krkapartments.module.user.User;
import com.example.krkapartments.module.user.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*@Service
public class RestConsumerImpl implements RestConsumer {

    private final RestTemplate restTemplate;

    @Value("${paymentservice.url}")
    private String paymentServiceUrl;

    private final String startTransactionUrl = "api/transaction/request";

    public RestConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void getDataForUserCreation(UserDto userDto) {
         restTemplate.postForEntity(paymentServiceUrl + startTransactionUrl, userDto, User.class);
    }
}*/

