package com.example.krkapartments.consumer;

import com.example.krkapartments.module.user.User;
import com.example.krkapartments.module.user.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestConsumerImpl implements RestConsumer {

    private final RestTemplate restTemplate;

    @Value("${paymentservice.url}")
    private String paymentServiceUrl;

  //  private final String employeeUrl = "employeeReport/";
  //  private final String projectUrl = "projectReport/";

    public RestConsumerImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    @Override
//    public User getDataForUserCreation(UserDto userDto) {
///*        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add(HttpHeaders.AUTHORIZATION, timeTrackerToken);*/
//       // HttpEntity<EmployeeReportFilter> entity = new HttpEntity<>(employeeReportFilter, headers);
//        return restTemplate.postForObject(paymentServiceUrl + employeeUrl, User.class);
//    }
//
//    @Override
//    public ProjectReport getDataForProjectReport(ProjectReportFilter projectReportFilter) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add(HttpHeaders.AUTHORIZATION, timeTrackerToken);
//        HttpEntity<ProjectReportFilter> entity = new HttpEntity<>(projectReportFilter, headers);
//        return restTemplate.postForObject(timeTrackerUrl + projectUrl, entity, ProjectReport.class);
//    }

}

