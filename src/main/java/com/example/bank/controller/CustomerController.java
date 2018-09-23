package com.example.bank.controller;

import com.example.bank.entity.AccountTransaction;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.BankTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CustomerController {

    private final BankTransactionRepo bankTransactionRepo;

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public CustomerController(BankTransactionRepo bankTransactionRepo, BankAccountRepository bankAccountRepository) {

        this.bankTransactionRepo = bankTransactionRepo;
        this.bankAccountRepository = bankAccountRepository;
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
            @AuthenticationPrincipal Customer user//,
          //  Map<String, Object> model
    ) {
      //  model.put("greeting", "Привет " + user.getUsername());

        return "bankAccounts";
    }

    @PostMapping("/replenishment")
    public String replenishment(@AuthenticationPrincipal Customer customer,
                                    @RequestParam String bankAccount,
                                    @RequestParam String cash){

        List<BankAccount> bankAccounts = customer.getAccounts().stream().filter(o -> o.getNameAccount().equals(bankAccount)).collect(Collectors.toList());

       if (!bankAccounts.isEmpty()){
           bankAccounts.get(0).setBalance(BigDecimal.valueOf(Long.parseLong(cash)));
           bankAccountRepository.save(bankAccounts.get(0));
       }

        return "replenishment";
    }

    @GetMapping("/replenishment")
    public String getReplenishment(@AuthenticationPrincipal Customer customer, Map<String, Object> model){

        model.put("bankAccounts", customer.getAccounts());

        return "replenishment";
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
