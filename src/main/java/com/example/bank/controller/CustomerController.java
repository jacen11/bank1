package com.example.bank.controller;

import com.example.bank.domain.Transfer;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.MyTransactionRepo;
import com.example.bank.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class CustomerController {


    private final TransferService customerService;

    private final MyTransactionRepo myTransactionRepo;

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public CustomerController(TransferService customerService, MyTransactionRepo myTransactionRepo, BankAccountRepository bankAccountRepository) {
        this.customerService = customerService;
        this.myTransactionRepo = myTransactionRepo;
        this.bankAccountRepository = bankAccountRepository;
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
        model.put("greeting", "Привет " + user.getUsername());

        if (nameAccount != null && !nameAccount.isEmpty()) {
            // FIXME: 19.09.2018 
            BankAccount bankAccount = new BankAccount();
            bankAccountRepository.save(bankAccount);
            model.put("success", "Cчет " + bankAccount.getNameAccount() + " успешно добавлен");
        }

        if (deleteNameAccount != null) {
//            BankAccount findBankAccount3 = bankAccountRepository.findBankAccountById(deleteNameAccount);
            //model.put("bankAccount", deleteNameAccount.substring(1));
            // FIXME: 19.09.2018 
//            if (findBankAccount3 != null && findBankAccount3.getCustomer().getId().equals(user.getId())) {
//                model.put("success", "Cчет " + findBankAccount3.getNumberBankAccount() + " успешно удален");
//                bankAccountRepository.delete(findBankAccount3);
//            } else {
//                model.put("success", "Cчет " + deleteNameAccount + " не найден");
//            }
        }
//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        //Iterable<BankAccount> bankAccounts = user.getAccounts();
//        model.put("bankAccounts", bankAccounts);
        //model.put("bankAccount", "счет не найден");

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
        model.put("greeting", "Привет " + user.getUsername());

//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        model.put("bankAccounts", bankAccounts);

        return "bankAccounts";
    }

    @GetMapping("/transfer")
    public ModelAndView transfer(@AuthenticationPrincipal Customer user, Map<String, Object> model) {

//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        model.put("bankAccounts", bankAccounts);

        return new ModelAndView("transfer");
    }

    @PostMapping("/transfer")
    public String add(
            @AuthenticationPrincipal Customer user,
            @RequestBody Transfer transfer,
            Map<String, Object>model
    ) {
//        List<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        model.put("bankAccounts", bankAccounts);


        customerService.transfer(transfer);

//        if (accountTo.substring(0, 1).equals("57")) {
//            if (accountTo.substring(2).isEmpty() || bankAccountRepository.existsById(Integer.valueOf(accountTo.substring(2)))) {
//                model.put("error", accountTo + " не найден");
//            }
//        }


//        if (customerRepository.loadUserByUsername(customerTo) == null) {
//            model.put("bankAccount", customerTo);
//            return "redirect:/error";
//        }
//        if (user.getAccounts().stream().map(BankAccount::getId).anyMatch(accountTo::equals)) {
//            //customerService.transfer(user, accountTo,amount, comment);
//        }
//        MyTransaction myTransaction = new MyTransaction(user, customerRepository.loadUserByUsername(customerTo), amount);
//
//        myTransactionRepo.save(myTransaction);
//        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();
//        model.put("myTransactions", myTransactions);
        return "transfer";
    }

    //с помощью jackson



    @PostMapping("/generation")
    public String generation(@AuthenticationPrincipal Customer user,
                             @RequestParam LocalDate from,
                             @RequestParam LocalDate to,
                             @RequestParam String bankAccount,
                             Map<String, Object> model){
//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        model.put("bankAccounts", bankAccounts);

      // myTransactionRepo.findAllByAccount(bankAccountRepository.)
        return "generation";
    }

    @GetMapping("/generation")
    public String openGeneration(@AuthenticationPrincipal Customer user,
                             Map<String, Object> model) throws IOException {
//        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAllByCustomer(user);
//        model.put("bankAccounts", bankAccounts);
//        JsonFactory factory = new JsonFactory();
//
//        JsonGenerator generator = factory.createGenerator(
//                new File("Общий отчет " + LocalDate.now().toString() + ".json"), JsonEncoding.UTF8);

//        List<MyTransaction> transactions = user
//                .getAccounts()
//                .stream()
//                .filter(account -> account.getId().equals(bankAccountId))
//                .map(BankAccount::getTransactions)
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();




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
