package com.example.bank.controller;

import com.example.bank.entity.Customer;
import com.example.bank.entity.MyTransaction;
import com.example.bank.repostory.CustomerRepository;
import com.example.bank.repostory.MyTransactionRepo;
import com.example.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;
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
            model.put("message",customerTo);
            return "redirect:/error";
        }

        MyTransaction myTransaction = new MyTransaction(user, customerRepository.loadUserByUsername(customerTo), amount);

        myTransactionRepo.save(myTransaction);
        Iterable<MyTransaction> myTransactions = myTransactionRepo.findAll();
        model.put("myTransactions", myTransactions);
        return "redirect:/transfer";
    }

}
