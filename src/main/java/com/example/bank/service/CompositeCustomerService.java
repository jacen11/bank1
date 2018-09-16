package com.example.bank.service;

import com.example.bank.entity.BankAccount;
import com.example.bank.repostory.CustomerRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

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
     * @param accountFrom клиент-отправитель
     * @param accountIdTo           идентикатор получателя
     * @param amount       сумма перевода
     */
    @Override
    public void transfer(BankAccount accountFrom, Long accountIdTo, BigDecimal amount) {
        String otherBankId = accountIdTo.toString().substring(0, 2);
        if (BankAccount.bankId.equalsIgnoreCase(otherBankId)) {
            internalCustomerService.transfer(accountFrom, accountIdTo, amount);
        }
        externalCustomerServices
                .stream()
                .filter(externalCustomerService ->externalCustomerService.getBankId().equalsIgnoreCase(otherBankId))
                .findAny()
                .ifPresent(service -> service.transfer(accountFrom, accountIdTo, amount));
    }
}
