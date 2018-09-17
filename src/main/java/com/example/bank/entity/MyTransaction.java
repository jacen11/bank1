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

    @JoinColumn(name = "account_from")
    @ManyToOne
    private BankAccount accountFrom;

    @JoinColumn(name = "account_to")
    @ManyToOne
    private BankAccount accountTo;

    private BigDecimal cash;

    private LocalDateTime dateTime;

    private String comment;


    public MyTransaction() {
    }

    public MyTransaction(BankAccount accountFrom, BankAccount accountTo, BigDecimal cash) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.cash = cash;
        dateTime = LocalDateTime.now();
    }
}
