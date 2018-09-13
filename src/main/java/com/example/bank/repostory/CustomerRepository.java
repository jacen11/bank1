package com.example.bank.repostory;

import com.example.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> , UserDetailsService {

    @Override
    default Customer loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s);
    }

   Customer findByUsername(String username);
}
