package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.type.AccountId;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends RefreshableJpaRepository<BankAccount, AccountId> {

  //  @Override
  //  @EntityGraph("abc")
    List<BankAccount> findAll();
    Optional<BankAccount> findById(AccountId accountId);
}