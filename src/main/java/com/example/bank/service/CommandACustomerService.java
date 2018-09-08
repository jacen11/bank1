package com.example.bank.service;

import com.example.bank.entity.Customer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CommandACustomerService implements ExternalCustomerService {


    @Override
    public void transfer(Customer customerFrom, Long id, BigDecimal amount) {

    }
}
