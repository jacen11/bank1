package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.AccountTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MyTransactionRepo extends CrudRepository<AccountTransaction, Long> {

    List<AccountTransaction> findByDateTimeBetween(LocalDate from, LocalDate to);
    List<AccountTransaction> findAllByAccountTo (BankAccount bankAccount);

    @Query("select a from #{#entityName} a where a.accountFrom = :bankAccount or a.accountTo = :bankAccount")
    List<AccountTransaction> findByAccount(@Param("bankAccount") BankAccount bankAccount);

}
