package com.example.bank.service;

import com.example.bank.entity.Customer;
import com.example.bank.repostory.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Service
@Primary
public class CompositeCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerService internalCustomerService;

    private final Collection<ExternalCustomerService> externalCustomerServices;


    public CompositeCustomerService(
            CustomerRepository customerRepository,
            @Qualifier(INTERNAL_CUSTOMER_SERVICE) CustomerService internalCustomerService,
            Collection<ExternalCustomerService> externalCustomerServices
    ) {
        this.customerRepository = customerRepository;
        this.internalCustomerService = internalCustomerService;
        this.externalCustomerServices = externalCustomerServices;
    }

    /**
     * Если получатель является клиентом нашего банка, то переводим деньги внутри собственной системы.
     * <p>Иначе, переводим во внешние банки
     *
     * @param customerFrom клиент-отправитель
     * @param id           идентикатор получателя
     * @param amount       сумма перевода
     */
    @Override
    public void transfer(Customer customerFrom, Long id, BigDecimal amount) {
        boolean exists = customerRepository.existsById(id);
        if (exists) {
            internalCustomerService.transfer(customerFrom, id, amount);
            return;
        }
        externalCustomerServices.forEach(service -> service.transfer(customerFrom, id, amount));
    }
}
