package com.example.bank.service.transfer;

import com.example.bank.domain.Transfer;

/**
 * Общий сервис для всех переводов
 */
public interface TransferService {

    String INTERNAL_CUSTOMER_SERVICE = "internal-transfer-service";

    void transfer(Transfer transfer);


}
