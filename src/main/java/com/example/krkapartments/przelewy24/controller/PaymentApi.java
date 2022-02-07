package com.example.paymentservice.przelewy24.controller;


import com.example.paymentservice.przelewy24.service.PaymentService;
import com.example.paymentservice.przelewy24.service.request.ReturnTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api")
public class PaymentApi {

    PaymentService paymentService;

    public PaymentApi(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public void hello() {
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void test() throws IOException {
        paymentService.test();
    }


    @PostMapping("/transaction/request")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ClientTransactionResponse> startTransaction(@RequestBody @Valid ClientTransactionRequest request) throws IOException {
        return new ResponseEntity<>(paymentService.sendTransactionRequest(request), HttpStatus.OK);
    }

    @PostMapping("/transaction/return")
    @ResponseStatus(HttpStatus.OK)
    public void returnTransaction(@RequestBody @Valid ReturnTransactionRequest request) throws IOException {
        paymentService.checkTransaction(request);
    }
}
