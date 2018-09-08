package com.example.bank.controller;

import com.example.bank.domain.Role;
import com.example.bank.entity.Customer;
import com.example.bank.repostory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(Customer user, Map<String, Object> model) {
        Customer userFromDb = customerRepository.loadUserByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "Пользователь уже есть!");
            return "registration";
        }

    //    user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        customerRepository.save(user);

        return "redirect:/login";
    }
}
