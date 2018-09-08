package com.example.bank.service;

import com.example.bank.domain.TransactionStatus;
import com.example.bank.entity.Customer;
import com.example.bank.repostory.CustomerRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static com.example.bank.service.CustomerService.INTERNAL_CUSTOMER_SERVICE;
import static java.util.Objects.requireNonNull;

@Service(INTERNAL_CUSTOMER_SERVICE)
public class InternalCustomerService implements CustomerService {


    private final CustomerRepository customerRepository;

    public InternalCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = requireNonNull(customerRepository);
    }

    @Transactional
    @Override
    public void transfer(Customer customerFrom, Long customerIdTo, BigDecimal amount) {
//        BigDecimal from = customerFrom.getCash();
//        customerFrom.setCash(from.subtract(amount));
//        customerTo.setCash(customerTo.getCash().add(amount));
//        customerRepository.save(customerFrom);
//        customerRepository.save(customerTo);
    }
}
