package com.example.bank;

import com.example.bank.entity.Customer;
import com.example.bank.repostory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local-run")
public class TestRunner implements ApplicationRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        Customer savedCustomer = customerRepository.save(customer);

        customerRepository.findAll().forEach(System.out::println);
    }
}
