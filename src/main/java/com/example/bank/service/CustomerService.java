package com.example.bank.service;

import com.example.bank.entity.BankAccount;

import java.math.BigDecimal;

public interface CustomerService {

    String INTERNAL_CUSTOMER_SERVICE = "internal-accountFrom-service";

    void transfer(BankAccount accountFrom, Long accountIdTo, BigDecimal amount);


}
