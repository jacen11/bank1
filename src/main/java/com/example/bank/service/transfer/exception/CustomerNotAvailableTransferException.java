package com.example.bank.service.transfer.exception;

import com.example.bank.domain.AccountId;

public class CustomerNotAvailableTransferException extends TransferException {

    public CustomerNotAvailableTransferException(AccountId fromAccount) {
    }
}
