package com.example.krkapartments.business.payment;

import com.example.krkapartments.config.ApplicationConfiguration;
import com.example.krkapartments.endpoint.payment.dto.*;
import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.persistence.payment.entity.TransactionEntity;
import com.example.krkapartments.persistence.payment.repository.TransactionRepository;
import com.example.krkapartments.persistence.shared.TransactionStatus;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import static java.lang.String.format;

@Service("PaymentService")
public class PaymentService {

    private final TransactionRepository repository;
    private final Gson gson;
    private final ApplicationConfiguration applicationConfiguration;

    public PaymentService(TransactionRepository repository, Gson gson, ApplicationConfiguration applicationConfiguration) {
        this.repository = repository;
        this.gson = gson;
        this.applicationConfiguration = applicationConfiguration;
    }

    public static String encrypt(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void test() throws IOException {
        HttpGet request = new HttpGet(applicationConfiguration.getTestUrl());
        createRequestWithAuth(request, applicationConfiguration.getUsername().toString(), applicationConfiguration.getPassword());
        executeRequest(request);
    }

    public void saveTransaction(TransactionEntity transactionEntity) {
        repository.save(transactionEntity);
    }

    public void checkTransaction(ReturnTransactionRequest request) throws IOException {
        String calculatedSign = encrypt(format("{\"merchantId\":%s,\"posId\":%s,\"sessionId\":\"%s\",\"amount\":%s,\"originAmount\":%s,\"currency\":\"%s\",\"orderId\":%s,\"methodId\":%s,\"statement\":\"%s\",\"crc\":\"%s\"}",
                request.getMerchantId(), request.getPosId(), request.getSessionId(), request.getAmount(), request.getOriginAmount(), request.getCurrency(), request.getOrderId(), request.getMethodId(), request.getStatement(), applicationConfiguration.getCrc()));
        if (request.getSign().equals(calculatedSign)) {
            TransactionEntity transactionEntity = repository.findByMerchantIdAndPosIdAndSessionIdAndAmountAndCurrency(request.getMerchantId(), request.getPosId(), request.getSessionId(), request.getOriginAmount(), request.getCurrency()).orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
            transactionEntity.setOrderId(request.getOrderId());
            transactionEntity.setStatus(TransactionStatus.SUCCESS.getStatus());
            repository.save(transactionEntity);
            VerifyTransactionRequest transactionRequest = createVerificationRequest(transactionEntity);
            sendVerificationRequest(transactionRequest);
        }
    }

    public void sendVerificationRequest(VerifyTransactionRequest request) throws IOException {
        HttpPut httpRequest = new HttpPut(applicationConfiguration.getVerifyUrl());
        createRequestWithAuth(httpRequest, applicationConfiguration.getUsername().toString(), applicationConfiguration.getPassword());
        StringEntity entity = new StringEntity(gson.toJson(request),
                ContentType.APPLICATION_JSON);
        httpRequest.setEntity(entity);
        executeRequest(httpRequest);

    }

    public VerifyTransactionRequest createVerificationRequest(TransactionEntity transactionEntity) {
        VerifyTransactionRequest transactionRequest = new VerifyTransactionRequest(transactionEntity);
        transactionRequest.setSign(encrypt(format("{\"sessionId\":\"%s\",\"orderId\":%s,\"amount\":%s,\"currency\":\"%s\",\"crc\":\"%s\"}", transactionEntity.getSessionId(), transactionEntity.getOrderId(), transactionEntity.getAmount(), transactionEntity.getCurrency(), applicationConfiguration.getCrc())));
        return transactionRequest;
    }

    public RegisterTransactionRequest createTransactionRequest(ClientTransactionRequest clientRequest) {
        int amount = clientRequest.getAmount();
        String description = clientRequest.getDescription();
        String email = clientRequest.getEmail();
        String sessionId = "" + new Date().getTime();
        return new RegisterTransactionRequestBuilder()
                .setMerchantId(applicationConfiguration.getUsername())
                .setPosId(applicationConfiguration.getUsername())
                .setSessionId(sessionId)
                .setAmount(amount)
                .setCurrency("PLN")
                .setDescription(description)
                .setEmail(email)
                .setCountry("PL")
                .setLanguage("pl")
                .setUrlReturn("http://www.localhost:3000/")
                .setUrlStatus("http://www.localhost:3000/")
                .setSign(encrypt(format("{\"sessionId\":\"%s\",\"merchantId\":%s,\"amount\":%s,\"currency\":\"%s\",\"crc\":\"%s\"}", sessionId, applicationConfiguration.getUsername(), amount, "PLN", applicationConfiguration.getCrc())))
                .build();
    }

    public ClientTransactionResponse sendTransactionRequest(ClientTransactionRequest request) throws IOException {
        RegisterTransactionRequest transactionRequest = createTransactionRequest(request);

        HttpPost httpRequest = new HttpPost(applicationConfiguration.getTransactionUrl());
        createRequestWithAuth(httpRequest, applicationConfiguration.getUsername().toString(), applicationConfiguration.getPassword());
        StringEntity entity = new StringEntity(gson.toJson(transactionRequest),
                ContentType.APPLICATION_JSON);
        httpRequest.setEntity(entity);
        saveTransaction(new TransactionEntity(transactionRequest.getMerchantId(), transactionRequest.getPosId(), transactionRequest.getSessionId(), transactionRequest.getAmount(), transactionRequest.getCurrency(), null, TransactionStatus.CREATED.getStatus()));
        String result = executeRequest(httpRequest);
        TransactionResponse transactionResponse = gson.fromJson(result, TransactionResponse.class);
//        Thread createUser = new Thread() {
//            public void run() {
//                restConsumer.sendDataForUserCreation(createUserDto(request));
//            }
//        };
//        createUser.start();
        return new ClientTransactionResponse(transactionRequest, transactionResponse.getData().getToken());
    }

    private String executeRequest(HttpRequestBase httpRequest) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(httpRequest);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
        }
        return EntityUtils.toString(response.getEntity());
    }

    private void createRequestWithAuth(HttpRequestBase request, String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(StandardCharsets.ISO_8859_1));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
    }

    private UserDto createUserDto(ClientTransactionRequest request) {
        String fullName = request.getClient();
        int idx = fullName.lastIndexOf(' ');
        if (idx == -1)
            throw new IllegalArgumentException("Only a single name: " + fullName);
        String firstName = fullName.substring(0, idx);
        String lastName = fullName.substring(idx + 1);

        return UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(request.getEmail())
                .telephoneNumber(request.getPhone())
                .build();
    }
}
