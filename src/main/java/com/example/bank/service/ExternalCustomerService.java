package com.example.bank.service;

import com.example.bank.repostory.CustomerRepository;

public interface ExternalCustomerService extends CustomerService {

    String getBankId();
}
