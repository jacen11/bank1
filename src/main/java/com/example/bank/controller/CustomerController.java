package com.example.bank.controller;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.entity.MyTransaction;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.CustomerRepository;
import com.example.bank.repostory.MyTransactionRepo;
import com.example.bank.service.CustomerService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MyTransactionRepo myTransactionRepo;
    @Autowired
    private BankAccountRepository bankAccountRepository;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "redirect:/bankAccounts";
    }

    //    @PostMapping("/transfer")
//    //@PreAuthorize("hasRole('USER')")
//    public View transfer(@AuthenticationPrincipal Customer accountFrom, Long customerTo, BigDecimal amount) {
//        customerService.transfer(accountFrom, customerTo, amount);
//        return new RedirectView("/transfer");
//    }

    @PostMapping("/bankAccounts")
    public String createBankAccount(
            @AuthenticationPrincipal Customer user,
            @RequestParam(required = false) String nameAccount,
            @RequestParam(required = false) Long deleteNameAccount,
            Map<String, Object> model
    ) {
        model.put("greeting", "Привет "+ user.getUsername());

        if (nameAccount != null && !nameAccount.isEmpty()) {
            BankAccount bankAccount = new BankAccount(user, nameAccount);
            bankAccountRepository.save(bankAccount);
            model.put("success", "Cчет " + bankAccount.getNameAccount() + " успешно добавлен");
        }

        if (deleteNameAccount != null) {
            BankAccount findBankAccount3 = bankAccountRepository.findBankAccountById(deleteNameAccount);
            //model.put("message", deleteNameAccount.substring(1));
            if (findBankAccount3 != null && findBankAccount3.getCustomer().getId().equals(user.getId())) {
                model.put("success", "Cчет " + findBankAccount3.getNumberBankAccount() + " успешно удален");
                bankAccountRepository.delete(findBankAccount3);
            } else {
                model.put("success", "Cчет " + deleteNameAccount + " не найден");
            }
        }

        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
        model.put("bankAccounts", bankAccounts);
        //model.put("message", "счет не найден");

        //BankAccount findBankAccount2 = bankAccountRepository.findBankAccountByNumberBankAccount(deleteNameAccount);
//        BankAccount findBankAccount3 = bankAccountRepository.findBankAccountById(deleteNameAccount);
//        if (findBankAccount3 != null) {
//           // user.getAccounts().remove(findBankAccount3);
//        }


        return "bankAccounts";
    }

//    @PostMapping("/bankAccounts")
//    public String deleteBankAccount(
//            @AuthenticationPrincipal Customer user,
//            @RequestParam String deleteNameAccount,
//            Map<String, Object> model
//    ) {
//
//       // BankAccount bankAccount = bankAccountRepository.findBankAccountById(deleteNameAccount);
//       // user.getAccounts().remove(bankAccount);
//        return "bankAccounts";
//    }

    @GetMapping("/bankAccounts")
    public String bankAccount(
            @AuthenticationPrincipal Customer user,
            Map<String, Object> model
    ) {
        model.put("greeting", "Привет "+ user.getUsername());

        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
        model.put("bankAccounts", bankAccounts);

        return "bankAccounts";
    }

    @GetMapping("/transfer")
    public ModelAndView transfer( @AuthenticationPrincipal Customer user,Map<String, Object> model) {

        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
        model.put("bankAccounts", bankAccounts);

        return new ModelAndView("transfer");
    }

    @PostMapping("/transfer")
    public String add(
            @AuthenticationPrincipal Customer user,
            @RequestParam Long accountTo,
            @RequestParam BigDecimal amount,
            @RequestParam(required = false) String comment,
            Map<String, Object> model
    ) {
        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
        model.put("bankAccounts", bankAccounts);

//        if (customerRepository.loadUserByUsername(customerTo) == null) {
//            model.put("message", customerTo);
//            return "redirect:/error";
//        }
//        if (user.getAccounts().stream().map(Account::getId).anyMatch(accountTo::equals)) {
//            customerService.transfer();
//        }
//        MyTransaction myTransaction = new MyTransaction(user, customerRepository.loadUserByUsername(customerTo), amount);
//
//        myTransactionRepo.save(myTransaction);
//        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();
//        model.put("myTransactions", myTransactions);
        return "redirect:/transfer";
    }

    //с помощью jackson
    @GetMapping("/generation")
    public String generation(@AuthenticationPrincipal Customer user, Map<String, Object> model) throws IOException {
        JsonFactory factory = new JsonFactory();

        JsonGenerator generator = factory.createGenerator(
                new File("Общий отчет " + LocalDate.now().toString() + ".json"), JsonEncoding.UTF8);


        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();


//        myTransactions.forEach(myTransaction -> {
//
//            try {
//                generator.writeStartObject();
//                generator.writeStringField("Имя отправителя", myTransaction.getAccountFrom().getUsername());
//                generator.writeStringField("Имя получателя", myTransaction.getCustomerTo().getUsername());
//                generator.writeStringField("Дата перевода", myTransaction.getDateTime().toString());
//                generator.writeNumberField("Сумма", myTransaction.getCash());
//                generator.writeEndObject();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        });
//
//
//        generator.close();
        return "generation";
    }

}
