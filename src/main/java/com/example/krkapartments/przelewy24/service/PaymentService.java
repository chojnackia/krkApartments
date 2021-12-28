package com.example.krkapartments.przelewy24.service;

import com.example.krkapartments.module.user.User;
import com.example.krkapartments.module.user.UserRepository;
import com.example.krkapartments.przelewy24.configuration.ApplicationConfiguration;
import com.example.krkapartments.przelewy24.controller.ClientTransactionRequest;
import com.example.krkapartments.przelewy24.controller.ClientTransactionResponse;
import com.example.krkapartments.przelewy24.data.entity.Transaction;
import com.example.krkapartments.przelewy24.data.enums.TransactionStatus;
import com.example.krkapartments.przelewy24.repository.TransactionRepository;
import com.example.krkapartments.przelewy24.service.request.RegisterTransactionRequest;
import com.example.krkapartments.przelewy24.service.request.RegisterTransactionRequestBuilder;
import com.example.krkapartments.przelewy24.service.request.ReturnTransactionRequest;
import com.example.krkapartments.przelewy24.service.request.VerifyTransactionRequest;
import com.example.krkapartments.przelewy24.service.response.TransactionResponse;
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
import java.util.UUID;

import static java.lang.String.format;

@Service("PaymentService")
public class PaymentService {

    private final TransactionRepository repository;
    private final Gson gson;
    private final ApplicationConfiguration applicationConfiguration;
    private final UserRepository userRepository;

    public PaymentService(TransactionRepository repository, ApplicationConfiguration applicationConfiguration, UserRepository userRepository) {
        this.applicationConfiguration = applicationConfiguration;
        this.repository = repository;
        this.gson = new Gson();
        this.userRepository = userRepository;
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

    public void saveTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    public void checkTransaction(ReturnTransactionRequest request) throws IOException {
        String calculatedSign = encrypt(format("{\"merchantId\":%s,\"posId\":%s,\"sessionId\":\"%s\",\"amount\":%s,\"originAmount\":%s,\"currency\":\"%s\",\"orderId\":%s,\"methodId\":%s,\"statement\":\"%s\",\"crc\":\"%s\"}",
                request.getMerchantId(), request.getPosId(), request.getSessionId(), request.getAmount(), request.getOriginAmount(), request.getCurrency(), request.getOrderId(), request.getMethodId(), request.getStatement(), applicationConfiguration.getCrc()));
        if (request.getSign().equals(calculatedSign)) {
            Transaction transaction = repository.findByMerchantIdAndPosIdAndSessionIdAndAmountAndCurrency(request.getMerchantId(), request.getPosId(), request.getSessionId(), request.getOriginAmount(), request.getCurrency()).orElseThrow(() -> new EntityNotFoundException("Transaction not found"));
            transaction.setOrderId(request.getOrderId());
            transaction.setStatus(TransactionStatus.SUCCESS.getStatus());
            repository.save(transaction);
            VerifyTransactionRequest transactionRequest = createVerificationRequest(transaction);
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
    public VerifyTransactionRequest createVerificationRequest(Transaction transaction) {
        VerifyTransactionRequest transactionRequest = new VerifyTransactionRequest(transaction);
        transactionRequest.setSign(encrypt(format("{\"sessionId\":\"%s\",\"orderId\":%s,\"amount\":%s,\"currency\":\"%s\",\"crc\":\"%s\"}", transaction.getSessionId(), transaction.getOrderId(), transaction.getAmount(), transaction.getCurrency(), applicationConfiguration.getCrc())));
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
                .setUrlReturn("http://www.localhost:3000/apartments-list")
                .setUrlStatus("http://www.localhost:3000/apartments-list")
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
        saveTransaction(new Transaction(transactionRequest.getMerchantId(), transactionRequest.getPosId(), transactionRequest.getSessionId(), transactionRequest.getAmount(), transactionRequest.getCurrency(), null, TransactionStatus.CREATED.getStatus()));
        String result = executeRequest(httpRequest);
        TransactionResponse transactionResponse = gson.fromJson(result, TransactionResponse.class);
        userRepository.save(addUser(request));

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

    private User addUser(ClientTransactionRequest request) {
        String fullName = request.getClient();
        int idx = fullName.lastIndexOf(' ');
        if (idx == -1)
            throw new IllegalArgumentException("Only a single name: " + fullName);
        String firstName = fullName.substring(0, idx);
        String lastName = fullName.substring(idx + 1);

        return User.builder()
                .id(UUID.randomUUID())
                .firstName(firstName)
                .lastName(lastName)
                .email(request.getEmail())
                .telephoneNumber(request.getPhone()).build();
    }
}
