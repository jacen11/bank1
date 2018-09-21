package com.example.bank.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transfer {

    private Long id;
    private BigDecimal amount;
    private AccountId fromAccount;
    private AccountId toAccount;
    private Currency currency;

}
