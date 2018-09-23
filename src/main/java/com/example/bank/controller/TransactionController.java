package com.example.bank.controller;


import com.example.bank.entity.AccountTransaction;
import com.example.bank.repostory.BankTransactionRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final BankTransactionRepo bankTransactionRepo;

    public TransactionController(BankTransactionRepo bankTransactionRepo) {
        this.bankTransactionRepo = bankTransactionRepo;
    }

    @GetMapping
    public Iterable<AccountTransaction> list() {
        return bankTransactionRepo.findAll();
    }

    @GetMapping("{id}")
    public AccountTransaction getOne(@PathVariable("id") AccountTransaction accountTransaction) {
        return accountTransaction;
    }

}
