package com.example.bank.repostory;

import com.example.bank.domain.AccountId;
import com.example.bank.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, AccountId> {

  //  @Override
  //  @EntityGraph("abc")
    List<BankAccount> findAll();
}