package com.example.bank.controller;

import com.example.bank.entity.BankAccount;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.MyTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bankAccounts2")
public class BankAccountController {


    private final BankAccountRepository bankAccountRepository;

    @Autowired
    private MyTransactionRepo transactionRepo;

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
//
//    private Map<String, String> getMessage(@PathVariable String id) {
//        return messages.stream()
//                .filter(message -> message.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }
//
//    @PostMapping
//    public Map<String, String> create(@RequestBody Map<String, String> message) {
//        message.put("id", String.valueOf(counter++));
//
//        messages.add(message);
//
//        return message;
//    }
//
//    @PutMapping("{id}")
//    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
//        Map<String, String> messageFromDb = getMessage(id);
//
//        messageFromDb.putAll(message);
//        messageFromDb.put("id", id);
//
//        return messageFromDb;
//    }
//
//    @DeleteMapping("{id}")
//    public void delete(@PathVariable String id) {
//        Map<String, String> message = getMessage(id);
//
//        messages.remove(message);
//    }
}
