package com.example.krkapartments.przelewy24.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "przelewy24")
public class ApplicationConfiguration {
    private String transactionUrl;
    private String verifyUrl;
    private String testUrl;
    private Integer username;
    private String password;
    private String crc;
}
