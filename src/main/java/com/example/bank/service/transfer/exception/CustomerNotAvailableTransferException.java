package com.example.bank.service.transfer.exception;

import com.example.bank.entity.type.AccountId;

public class CustomerNotAvailableTransferException extends TransferException {

    public CustomerNotAvailableTransferException(AccountId fromAccount) {
    }
}
