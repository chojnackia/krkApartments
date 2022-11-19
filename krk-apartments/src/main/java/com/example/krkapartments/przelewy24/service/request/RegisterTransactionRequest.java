package com.example.krkapartments.przelewy24.service.request;

import lombok.*;
import org.springframework.lang.NonNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterTransactionRequest {
    @NonNull
    private Integer merchantId;
    @NonNull
    private Integer posId;
    @NonNull
    private String sessionId;
    @NonNull
    private Integer amount;
    @NonNull
    private String currency;
    @NonNull
    private String description;
    @NonNull
    private String email;
    @NonNull
    private String country;
    @NonNull
    private String language;
    @NonNull
    private String urlReturn;
    @NonNull
    private String urlStatus;
    @NonNull
    private String sign;
    private String client;
    private String address;
    private String zip;
    private String city;
    private String phone;
    private Integer method;
    private Integer timeLimit;
    private Integer channel;
    private boolean waitForResult;
    private boolean regulationAccept;
    private Integer shipping;
    private String transferLabel;
    private Integer mobileLib;
    private String sdkVersion;
    private String encoding;
    private String methodRefId;


}