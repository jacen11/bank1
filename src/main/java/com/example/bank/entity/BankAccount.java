package com.example.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "accounts")
@Getter
@Setter
public class BankAccount {

    public static final String bankId = "57";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", unique = true, nullable = false)
    private Long id;

    private BigDecimal balance;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String nameAccount;


    @OneToMany
    private List<MyTransaction> transactions;

    public BankAccount() {
    }

    public BankAccount(Customer customer, String nameAccount) {
        this.customer = customer;
        this.nameAccount = nameAccount;
        this.balance = BigDecimal.ZERO;
    }

    public String getNumberBankAccount() {
        return bankId + id;
    }

    public static boolean isInternal(Long id) {
        return id.toString().startsWith(bankId);
    }
}
