package com.example.bank.domain;

import com.example.bank.entity.type.AccountId;
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
