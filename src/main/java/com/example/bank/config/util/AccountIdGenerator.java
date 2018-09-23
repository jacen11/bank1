package com.example.bank.config.util;

import com.example.bank.domain.AccountId;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class AccountIdGenerator implements IdentifierGenerator {

    static final long SIZE = 8;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Long nextLong = ThreadLocalRandom.current().nextLong((long) Math.pow(10.0, SIZE - 1), (long) Math.pow(10, SIZE) - 1);
        return AccountId.fromOnlyId(nextLong);
    }

}