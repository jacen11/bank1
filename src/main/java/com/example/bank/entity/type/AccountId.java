package com.example.bank.entity.type;

import java.io.Serializable;

public class AccountId implements Serializable {

    static final long SIZE = 8;

    static final long FACTOR = (long) Math.pow(10.0, SIZE);

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
        if (bankIdAsInt != BANK_ID) {
            throw new IllegalArgumentException("Incorrect format: " + bankIdAsInt);
        }
        if (id == null) {
            throw new IllegalArgumentException("Required not null");
        }
        if (id > 0 && id.toString().length() != 8) {
            throw new IllegalArgumentException("Incorrect format: " + id);
        }
        return new AccountId(bankIdAsInt, id);
    }

    public static AccountId fromIdWithBankId(Long idWithBankId) {
        if (idWithBankId == null) {
            throw new IllegalArgumentException("Required not null");
        }
        long id = idWithBankId % FACTOR;
        long bankId = idWithBankId / FACTOR;
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

    public Integer getBankId() {
        return bankId;
    }

    public Long getId() {
        return id;
    }

    public Long get() {
        return bankId * FACTOR + id;
    }

    public boolean isInternal() {
        return bankId == BANK_ID;
    }

    @Override
    public String toString() {
        return get().toString();
    }
}