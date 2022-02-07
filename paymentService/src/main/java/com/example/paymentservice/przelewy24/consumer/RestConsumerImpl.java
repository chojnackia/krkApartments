package com.example.paymentservice.przelewy24.consumer;

import com.example.paymentservice.przelewy24.User.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestConsumerImpl implements RestConsumer {

    private final RestTemplate restTemplate;

    @Value("${krkApartments.url}")
    private String paymentServiceUrl;

    private final String postUserUrl = "user/";

    public RestConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void sendDataForUserCreation(UserDto userDto) {
/*        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(HttpHeaders.AUTHORIZATION, timeTrackerToken);*/
       // HttpEntity<EmployeeReportFilter> entity = new HttpEntity<>(employeeReportFilter, headers);
        restTemplate.postForEntity(paymentServiceUrl + postUserUrl, userDto ,UserDto.class);
    }

}

