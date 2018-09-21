package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.AccountTransaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MyTransactionRepo extends CrudRepository<AccountTransaction, Long> {

    List<AccountTransaction> findByDateTimeBetween(LocalDate from, LocalDate to);
    List<AccountTransaction> findAllByAccountTo (BankAccount bankAccount);

}
