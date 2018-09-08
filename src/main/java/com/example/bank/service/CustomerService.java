package com.example.bank.service;

import com.example.bank.domain.TransactionStatus;
import com.example.bank.entity.Customer;

import java.math.BigDecimal;

public interface CustomerService {

    String INTERNAL_CUSTOMER_SERVICE = "internal-customer-service";

    void transfer(Customer customerFrom, Long id, BigDecimal amount);


}
