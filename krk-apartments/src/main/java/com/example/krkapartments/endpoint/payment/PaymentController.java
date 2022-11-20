package com.example.krkapartments.endpoint.payment;


import com.example.krkapartments.business.payment.PaymentService;
import com.example.krkapartments.endpoint.payment.dto.ClientTransactionRequest;
import com.example.krkapartments.endpoint.payment.dto.ClientTransactionResponse;
import com.example.krkapartments.endpoint.payment.dto.ReturnTransactionRequest;
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
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
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
