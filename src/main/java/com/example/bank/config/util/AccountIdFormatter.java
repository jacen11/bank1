package com.example.bank.config.util;

import com.example.bank.entity.type.AccountId;
import org.springframework.format.Formatter;

import java.util.Locale;

public class AccountIdFormatter implements Formatter<AccountId> {

    @Override
    public AccountId parse(String s, Locale locale) {
        return AccountId.fromIdWithBankId(Long.valueOf(s));
    }

    @Override
    public String print(AccountId accountId, Locale locale) {
        return accountId.toString();
    }

}
