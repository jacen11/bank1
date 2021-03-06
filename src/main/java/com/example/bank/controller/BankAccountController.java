package com.example.bank.controller;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/account")
public class BankAccountController {


    private final BankAccountRepository bankAccountRepository;

    private final CustomerRepository customerRepository;

    public BankAccountController(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }


    @GetMapping
    public Iterable<BankAccount> list(@AuthenticationPrincipal Customer customer) {
        return customer.getAccounts();
    }

    @GetMapping("/{id}")
    public BankAccount getOne(@PathVariable("id") BankAccount bankAccount) {
        return bankAccount;
    }

    @PostMapping
    public BankAccount create(@AuthenticationPrincipal Customer customer,
                              @RequestBody BankAccount bankAccount) {
        bankAccount.setBalance(BigDecimal.ZERO);
        bankAccountRepository.save(bankAccount);
        customer.getAccounts().add(bankAccount);
        customerRepository.save(customer);
        return bankAccount;
    }

    @PutMapping("/{id}")
    public BankAccount update(
            @PathVariable("id") BankAccount bankAccountFromDb,
            @RequestBody BankAccount bankAccount
    ) {
        BeanUtils.copyProperties(bankAccount, bankAccountFromDb, "id");
        return bankAccountRepository.save(bankAccountFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }

}
