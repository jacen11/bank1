package com.example.bank.entity;

import com.example.bank.entity.type.AccountId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "accounts")
@Getter
@Setter
public class BankAccount {


    @Id
    @Type(type = "com.example.bank.entity.type.AccountIdType")
    @Column(name = "account_id", unique = true, nullable = false)
    @GeneratedValue(generator = "account-id-generator")
    @GenericGenerator(name = "account-id-generator", strategy = "com.example.bank.entity.type.AccountIdGenerator")
    private AccountId id;

    private BigDecimal balance;

    @Column(name = "name_account", nullable = false)
    private String nameAccount;

}
