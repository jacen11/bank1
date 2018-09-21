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
public class AccountTransaction {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id", unique = true, nullable = false)
    private Long id;

    @JoinColumn(name = "account_from")
    @ManyToOne(cascade = CascadeType.ALL)
    private BankAccount accountFrom;

    @JoinColumn(name = "account_to")
    @ManyToOne(cascade = CascadeType.ALL)
    private BankAccount accountTo;

    private BigDecimal cash;

    private LocalDateTime dateTime;

    private String comment;


    public AccountTransaction() {
    }

    public AccountTransaction(BankAccount accountFrom, BankAccount accountTo, BigDecimal cash) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.cash = cash;
        this.dateTime = LocalDateTime.now();
    }
}
