package com.example.paymentservice.przelewy24.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Transaction {

    @Column
    private int merchantId;
    @Column
    private int posId;

    @Id
    @Column(unique = true, nullable = false)
    private String sessionId;
    @Column
    private int amount;
    @Column
    private String currency;
    @Column
    private Integer orderId;
    @Column
    private String status;
}
