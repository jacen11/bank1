package com.example.bank.entity.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

import static com.example.bank.entity.type.AccountId.SIZE;

public class AccountIdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Long nextLong = ThreadLocalRandom.current().nextLong((long) Math.pow(10.0, SIZE - 1), (long) Math.pow(10, SIZE) - 1);
        return AccountId.fromOnlyId(nextLong);
    }

}