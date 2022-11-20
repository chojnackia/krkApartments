package com.example.krkapartments.endpoint.payment.dto;

import lombok.*;
import org.springframework.lang.NonNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterTransactionRequestBuilder {


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

    public RegisterTransactionRequest build() {
        return new RegisterTransactionRequest(merchantId, posId, sessionId, amount, currency, description, email, country, language, urlReturn, urlStatus, sign, client, address, zip, city, phone, method, timeLimit, channel, waitForResult, regulationAccept, shipping, transferLabel, mobileLib, sdkVersion, encoding, methodRefId);
    }

    public RegisterTransactionRequestBuilder setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public RegisterTransactionRequestBuilder setPosId(Integer posId) {
        this.posId = posId;
        return this;
    }

    public RegisterTransactionRequestBuilder setSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public RegisterTransactionRequestBuilder setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public RegisterTransactionRequestBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public RegisterTransactionRequestBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public RegisterTransactionRequestBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterTransactionRequestBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public RegisterTransactionRequestBuilder setLanguage(String language) {
        this.language = language;
        return this;
    }

    public RegisterTransactionRequestBuilder setUrlReturn(String urlReturn) {
        this.urlReturn = urlReturn;
        return this;
    }

    public RegisterTransactionRequestBuilder setUrlStatus(String urlStatus) {
        this.urlStatus = urlStatus;
        return this;
    }

    public RegisterTransactionRequestBuilder setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public RegisterTransactionRequestBuilder setClient(String client) {
        this.client = client;
        return this;
    }

    public RegisterTransactionRequestBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public RegisterTransactionRequestBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public RegisterTransactionRequestBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public RegisterTransactionRequestBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public RegisterTransactionRequestBuilder setMethod(Integer method) {
        this.method = method;
        return this;
    }

    public RegisterTransactionRequestBuilder setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public RegisterTransactionRequestBuilder setChannel(Integer channel) {
        this.channel = channel;
        return this;
    }

    public RegisterTransactionRequestBuilder setWaitForResult(boolean waitForResult) {
        this.waitForResult = waitForResult;
        return this;
    }

    public RegisterTransactionRequestBuilder setRegulationAccept(boolean regulationAccept) {
        this.regulationAccept = regulationAccept;
        return this;
    }

    public RegisterTransactionRequestBuilder setShipping(Integer shipping) {
        this.shipping = shipping;
        return this;
    }

    public RegisterTransactionRequestBuilder setTransferLabel(String transferLabel) {
        this.transferLabel = transferLabel;
        return this;
    }

    public RegisterTransactionRequestBuilder setMobileLib(Integer mobileLib) {
        this.mobileLib = mobileLib;
        return this;
    }

    public RegisterTransactionRequestBuilder setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
        return this;
    }

    public RegisterTransactionRequestBuilder setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    public RegisterTransactionRequestBuilder setMethodRefId(String methodRefId) {
        this.methodRefId = methodRefId;
        return this;
    }
}