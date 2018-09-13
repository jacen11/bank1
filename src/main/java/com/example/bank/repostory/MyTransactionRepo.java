package com.example.bank.repostory;

import com.example.bank.entity.MyTransaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MyTransactionRepo extends CrudRepository<MyTransaction, Long> {
    List<MyTransaction> findMyTransactionsByDateTimeAfter(LocalDateTime time);
}
