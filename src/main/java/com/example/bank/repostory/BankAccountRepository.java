package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.type.AccountId;

import java.util.List;

public interface BankAccountRepository extends RefreshableJpaRepository<BankAccount, AccountId> {

  //  @Override
  //  @EntityGraph("abc")
    List<BankAccount> findAll();
}