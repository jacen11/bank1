package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
    List<BankAccount> findAllByCustomer(Customer customer);
}
