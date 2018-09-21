package com.example.bank.service.transfer;

import com.example.bank.domain.Transfer;
import com.example.bank.entity.BankAccount;

import java.math.BigDecimal;

public interface TransferService {

    String INTERNAL_CUSTOMER_SERVICE = "internal-transfer-service";

    void transfer(Transfer transfer);


}
