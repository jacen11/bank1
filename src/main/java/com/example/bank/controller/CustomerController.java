package com.example.bank.controller;

import com.example.bank.entity.AccountTransaction;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.repostory.BankTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    private final BankTransactionRepo bankTransactionRepo;

    @Autowired
    public CustomerController(BankTransactionRepo bankTransactionRepo) {

        this.bankTransactionRepo = bankTransactionRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "redirect:/bankAccounts";
    }

    @GetMapping("/transfer")
    public String transfer(){
        return "transfer";
    }

    @GetMapping("/test")
    public String test3(){
        return "test";
    }

    @GetMapping("/bankAccounts")
    public String bankAccount(
            @AuthenticationPrincipal Customer user,
            Map<String, Object> model
    ) {
        model.put("greeting", "Привет " + user.getUsername());

//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        model.put("bankAccounts", bankAccounts);

        return "bankAccounts";
    }


    @PostMapping("/generationReport")
    public List<AccountTransaction> generationReport(@AuthenticationPrincipal Customer customer,
                                                     @RequestParam String from,
                                                     @RequestParam String to,
                                                     @RequestParam String bankAccount,
                                                     Map<String, Object> model){
        //TODO найти аккаунт
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        List<AccountTransaction> accountTransactions = bankTransactionRepo.findByDateTimeBetween(fromDate.atStartOfDay(),toDate.atStartOfDay());//.stream().filter(p -> p.getAccountFrom().equals(bankAccount) || p.getAccountTo().equals(bankAccount)).collect(Collectors.toList());

        return accountTransactions;
    }

    @GetMapping("/generationReport")
    public String openGeneration(@AuthenticationPrincipal Customer customer,
                             Map<String, Object> model) throws IOException {
        Iterable<BankAccount> bankAccounts = customer.getAccounts();
        model.put("bankAccounts", bankAccounts);

        return "generationReport";
    }

}
