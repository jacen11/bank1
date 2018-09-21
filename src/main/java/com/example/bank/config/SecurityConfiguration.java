package com.example.bank.config;

import com.example.bank.repostory.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/registration", "/static/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()                                       //временно
                .formLogin().disable()//.loginPage("/login").permitAll()
//                .and()
                .logout()
                ;
    }

    //customerRepository
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customerRepository)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                ;
    }
}
