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

//    @Autowired
//    public SecurityConfiguration(@Qualifier("customerRepository") UserDetailsService customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()                                       //временно
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .permitAll();
    }

    //customerRepository
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerRepository)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
