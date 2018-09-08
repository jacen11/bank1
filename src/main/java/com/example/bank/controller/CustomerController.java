package com.example.bank.controller;

import com.example.bank.entity.Customer;
import com.example.bank.service.CustomerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.math.BigDecimal;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/transfer")
    public ModelAndView transfer() {
        return new ModelAndView("transfer");
    }

    @PostMapping("/transfer")
    //@PreAuthorize("hasRole('USER')")
    public View transfer(@AuthenticationPrincipal Customer customer, Long customerTo, BigDecimal amount) {
        customerService.transfer(customer, customerTo, amount);
        return new RedirectView("/transfer");
    }
}
