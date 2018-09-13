package com.example.bank.repostory;

import com.example.bank.entity.MyTransaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface MyTransactionRepo extends CrudRepository<MyTransaction, Long> {
    List<MyTransaction> findMyTransactionsByDateTimeAfter(LocalDateTime time);
    MyTransaction findMyTransactionByCustomer(String username);

    default List<MyTransaction> findMyTransactions(String username){
        List<MyTransaction> list = new ArrayList<>();
        list.add(findMyTransactionByCustomer(username));
        return list;
    }

}
