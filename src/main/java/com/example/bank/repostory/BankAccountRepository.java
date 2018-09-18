package com.example.bank.repostory;

import com.example.bank.entity.BankAccount;
import com.example.bank.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
    List<BankAccount> findAllByCustomer(Customer customer);
   // BankAccount findBankAccountByNumberBankAccount(String s);
    BankAccount findBankAccountById(Long s);
   // BankAccount findBankAccountByNumberBankAccount(String s);


    default BankAccount findBankAccountById(String id) {
        return findBankAccountById(id.substring(2));
    }


    default void deleteAccount(BankAccount bankAccount){
        delete(bankAccount);
    }


//    default BankAccount deleteAccount(Long numberBankAccount){
//        return findBankAccountById(numberBankAccount);
//    }
}
