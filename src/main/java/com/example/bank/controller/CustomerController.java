package com.example.bank.controller;

import com.example.bank.entity.Customer;
import com.example.bank.entity.MyTransaction;
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
import java.util.Map;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MyTransactionRepo myTransactionRepo;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "transfer";
    }

    @GetMapping("/transfer")
    public ModelAndView transfer() {
        return new ModelAndView("transfer");
    }

//    @PostMapping("/transfer")
//    //@PreAuthorize("hasRole('USER')")
//    public View transfer(@AuthenticationPrincipal Customer customer, Long customerTo, BigDecimal amount) {
//        customerService.transfer(customer, customerTo, amount);
//        return new RedirectView("/transfer");
//    }

    @PostMapping("/transfer")
    public String add(
            @AuthenticationPrincipal Customer user,
            @RequestParam String customerTo,
            @RequestParam BigDecimal amount, Map<String, Object> model
    ) {

        if (customerRepository.loadUserByUsername(customerTo) == null) {
            model.put("message", customerTo);
            return "redirect:/error";
        }

        MyTransaction myTransaction = new MyTransaction(user, customerRepository.loadUserByUsername(customerTo), amount);

        myTransactionRepo.save(myTransaction);
        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();
        model.put("myTransactions", myTransactions);
        return "redirect:/transfer";
    }

    //с помощью jackson
    @GetMapping("/generation")
    public String generation(@AuthenticationPrincipal Customer user, Map<String, Object> model) throws IOException {
        JsonFactory factory = new JsonFactory();

        JsonGenerator generator = factory.createGenerator(
                new File("Общий отчет " + LocalDate.now().toString() + ".json"), JsonEncoding.UTF8);


        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();


        myTransactions.forEach(myTransaction -> {

            try {
                generator.writeStartObject();
                generator.writeStringField("Имя отправителя", myTransaction.getCustomer().getUsername());
                generator.writeStringField("Имя получателя", myTransaction.getCustomerTo().getUsername());
                generator.writeStringField("Дата перевода", myTransaction.getDateTime().toString());
                generator.writeNumberField("Сумма", myTransaction.getCash());
                generator.writeEndObject();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        generator.close();
        return "generation";
    }

}
