package com.example.bank.domain;

import java.io.Serializable;

public class AccountId implements Serializable {

    private static final long SIZE = (long) Math.pow(10.0, 8.0);

    public static final int BANK_ID = 57;

    private final Integer bankId;
    private final Long id;

    private AccountId(Integer bankId, Long id) {
        this.bankId = bankId;
        this.id = id;
    }

    public static AccountId of(Number bankId, Long id) {
        if (bankId == null) {
            throw new IllegalArgumentException("Required not null");
        }
        int bankIdAsInt = bankId.intValue();
        if (id == null) {
            throw new IllegalArgumentException("Required not null");
        }
        if (id > 0 && Math.log10(id.doubleValue()) + 1 == 8) {
            throw new IllegalArgumentException("Incorrect format: " + id);
        }
        return new AccountId(bankIdAsInt, id);
    }

    public static AccountId fromIdWithBankId(Long idWithBankId) {
        if (idWithBankId == null) {
            throw new IllegalArgumentException("Required not null");
        }
        long id = idWithBankId % SIZE;
        long bankId = idWithBankId / SIZE;
        return of(bankId, id);
    }

    public static AccountId fromOnlyId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Required not null");
        }
        return of(BANK_ID, id);
    }

    public static AccountId fromString(String raw) {
        return raw == null || raw.isEmpty() ? null : of(Integer.parseInt(raw.substring(0,2)), Long.parseLong(raw.substring(2)));
    }

    public boolean isInternal() {
        return bankId == BANK_ID;
    }

    public Integer getBankId() {
        return bankId;
    }

    public Long getId() {
        return id;
    }

    public Long get() {
        return bankId * SIZE + id;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}