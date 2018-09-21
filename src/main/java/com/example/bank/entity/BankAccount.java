package com.example.bank.entity;

import com.example.bank.domain.AccountId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "accounts")
@Getter
@Setter
public class BankAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", unique = true, nullable = false)
    @Type(type = "com.example.bank.entity.type.AccountIdType")
    private AccountId id;

    private BigDecimal balance;

    @Column(name = "name_account", nullable = false)
    private String nameAccount;


    @OneToMany
    private List<AccountTransaction> transactions;

    public BankAccount() {
    }



}
