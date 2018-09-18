package com.example.bank.repostory;

import com.example.bank.entity.Customer;
import com.example.bank.entity.MyTransaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface MyTransactionRepo extends CrudRepository<MyTransaction, Long> {

    List<MyTransaction> findByDateTimeBetween(LocalDateTime from, LocalDateTime to);
}
