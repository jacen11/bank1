package com.example.bank.service.transfer.exception;

import lombok.Getter;

@Getter
public class UnknownBankTransferException extends TransferException {

    private final Integer bankId;

    public UnknownBankTransferException(Integer bankId) {
        this.bankId = bankId;
    }

}
