package com.example.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Getter
@Setter
public class MyTransaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", unique = true, nullable = false)
    private Long id;
    private Customer customer;
    private Customer customerTo;
    private BigDecimal cash;
    private LocalDateTime dateTime;

    public MyTransaction() {
    }

    public MyTransaction(Customer customer, Customer customerTo, BigDecimal cash) {
        this.customer = customer;
        this.customerTo = customerTo;
        this.cash = cash;
        dateTime=LocalDateTime.now();
    }
}
