package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.AccountTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BankTransactionRepo extends CrudRepository<AccountTransaction, Long> {

    List<AccountTransaction> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);
    List<AccountTransaction> findAllByAccountTo (BankAccount bankAccount);

    @Query("select a from #{#entityName} a where a.accountFrom = :bankAccount or a.accountTo = :bankAccount")
    List<AccountTransaction> findByAccount(@Param("bankAccount") BankAccount bankAccount);

}
