package com.example.bank.config.util;

import com.example.bank.domain.AccountId;

import org.springframework.format.Formatter;
import java.text.ParseException;
import java.util.Locale;

public class AccountIdFormatter implements Formatter<AccountId> {

    @Override
    public AccountId parse(String s, Locale locale) throws ParseException {
        return AccountId.fromIdWithBankId(Long.valueOf(s));
    }

    @Override
    public String print(AccountId accountId, Locale locale) {
        return accountId.toString();
    }

}
