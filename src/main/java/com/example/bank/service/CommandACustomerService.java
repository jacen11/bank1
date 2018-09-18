package com.example.bank.service;

import com.example.bank.domain.Transfer;
import com.example.bank.entity.BankAccount;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;

@Service
public class CommandACustomerService implements ExternalCustomerService {

  //  private final RestOperations rest;


//    public CommandACustomerService(RestOperations rest) {
//        this.rest = rest;
//    }


    @Override
    public void transfer(BankAccount customerFrom, Long id, BigDecimal amount, String comment) {
        Transfer transfer  = new Transfer();
        RequestEntity<Transfer> requestEntity = RequestEntity.post(URI.create("адрес банка")).body(transfer);
      //  TransferResponse  response = rest.exchange(requestEntity, TransferResponse.class);

    }

    @Override
    public String getBankId() {
        return "09";
    }


}
