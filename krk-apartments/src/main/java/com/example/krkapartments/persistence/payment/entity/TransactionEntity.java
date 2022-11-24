package com.example.krkapartments.persistence.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

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
