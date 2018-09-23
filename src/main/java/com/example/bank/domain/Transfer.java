package com.example.bank.domain;

import com.example.bank.entity.type.AccountId;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class Transfer {

    private Long id;

    @Min(1)
    private BigDecimal amount;
    @NotNull(message = "transfer.from-acoount.not-null")
    private AccountId fromAccount;
    @NotNull
    private AccountId toAccount;
    private Currency currency;

}
