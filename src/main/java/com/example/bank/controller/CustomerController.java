package com.example.bank.controller;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.entity.type.AccountId;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.BankTransactionRepo;
import com.example.bank.service.report.ReportGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

    private final ReportGenerator reportGenerator;

    @Autowired
    public CustomerController(BankTransactionRepo bankTransactionRepo, BankAccountRepository bankAccountRepository, ReportGenerator reportGenerator) {

        this.bankTransactionRepo = bankTransactionRepo;
        this.bankAccountRepository = bankAccountRepository;
        this.reportGenerator = reportGenerator;
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

        List<BankAccount> bankAccounts = customer.getAccounts().stream().filter(o -> o.getId().toString().equals(bankAccount)).collect(Collectors.toList());

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
    public Resource generationReport(@AuthenticationPrincipal Customer customer,
                                     @RequestParam LocalDate from,
                                     @RequestParam LocalDate to,
                                     @RequestParam AccountId bankAccount,
                                     Map<String, Object> model){
        Resource report = reportGenerator.generate(from, to, bankAccount);
        return report;
    }

    @GetMapping("/generationReport")
    public String openGeneration(@AuthenticationPrincipal Customer customer,
                             Map<String, Object> model) throws IOException {
        Iterable<BankAccount> bankAccounts = customer.getAccounts();
        model.put("bankAccounts", bankAccounts);

        return "generationReport";
    }

}
