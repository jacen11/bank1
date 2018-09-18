package com.example.bank.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transfer {

    private Long id;
    private BigDecimal amount;
    private Long fromAccount;
    private Long toAccount;
    private Currency currency;

}
