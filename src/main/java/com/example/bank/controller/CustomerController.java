package com.example.bank.controller;

import com.example.bank.entity.AccountTransaction;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.entity.type.AccountId;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.BankTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String transfer() {
        return "transfer";
    }

    @GetMapping("/bankAccounts")
    public String bankAccount() {
        return "bankAccounts";
    }

    @PostMapping("/replenishment")
    public String replenishment(@AuthenticationPrincipal Customer customer,
                                @RequestParam AccountId bankAccount,
                                @RequestParam String cash) {

        List<BankAccount> bankAccounts = customer.getAccounts().stream().filter(o -> o.getId().equals(bankAccount)).collect(Collectors.toList());

        if (!bankAccounts.isEmpty()) {
            BankAccount tempBankAccount1 = bankAccounts.get(0);
            BigDecimal balance = tempBankAccount1.getBalance();
            tempBankAccount1.setBalance(balance.add(BigDecimal.valueOf(Long.parseLong(cash))));
            bankAccountRepository.save(tempBankAccount1);
        }

        return "replenishment";
    }

    @GetMapping("/replenishment")
    public String getReplenishment(@AuthenticationPrincipal Customer customer, Map<String, Object> model) {

        model.put("bankAccounts", customer.getAccounts());

        return "replenishment";
    }

    @PostMapping("/generationReport")
    @ResponseBody
    public List<AccountTransaction> generationReport(@AuthenticationPrincipal Customer customer,
                                                     @RequestParam String fromString,
                                                     @RequestParam String toString,
                                                     @RequestParam AccountId bankAccount,
                                                     Map<String, Object> model) {
        LocalDate from = LocalDate.parse(fromString);
        LocalDate to = LocalDate.parse(toString);
        BankAccount tempBankAccount = null;
        for (BankAccount e : customer.getAccounts()) {
            if (e.getId().equals(bankAccount)) {
                tempBankAccount = e;
            }
        }

        List<AccountTransaction> report = bankTransactionRepo.findByDateTimeBetween(from.atStartOfDay(), to.atStartOfDay(), tempBankAccount);
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
