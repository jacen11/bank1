package com.example.bank.controller;

import com.example.bank.domain.AccountId;
import com.example.bank.entity.BankAccount;
import com.example.bank.repostory.BankAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("bankAccounts2")
public class BankAccountController {


    private final BankAccountRepository bankAccountRepository;


    public BankAccountController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @GetMapping
    public Iterable<BankAccount> list() {
//        List<BankAccount> accounts = transactionRepo.findByAccount();
        return bankAccountRepository.findAll();
    }

    @GetMapping("{id}")
    public BankAccount getOne(@PathVariable("id") BankAccount bankAccount) {
        return bankAccount;
    }

    private static long counter = 1;

    @PostMapping
    public BankAccount create(@RequestBody BankAccount bankAccount) {

        bankAccount.setId( AccountId.of(57,counter++));
        bankAccount.setBalance(BigDecimal.ZERO);
        return bankAccountRepository.save(bankAccount);
    }

    @PutMapping("{id}")
    public BankAccount update(
            @PathVariable("id") BankAccount bankAccountFromDb,
            @RequestBody BankAccount bankAccount
    ) {
        BeanUtils.copyProperties(bankAccount, bankAccountFromDb, "id");

        return bankAccountRepository.save(bankAccountFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }

}
