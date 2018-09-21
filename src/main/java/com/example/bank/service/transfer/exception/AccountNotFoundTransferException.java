package com.example.bank.service.transfer.exception;

import com.example.bank.domain.AccountId;
import lombok.Getter;

@Getter
public class AccountNotFoundTransferException extends TransferException {

    private final AccountId accountId;

    public AccountNotFoundTransferException(AccountId accountId) {
        this.accountId = accountId;
    }
}
