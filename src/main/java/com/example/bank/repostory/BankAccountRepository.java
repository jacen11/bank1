package com.example.bank.repostory;

import com.example.bank.domain.AccountId;
import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, AccountId> {

}
