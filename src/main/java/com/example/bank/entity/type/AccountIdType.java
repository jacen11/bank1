package com.example.bank.entity.type;

import com.example.bank.domain.AccountId;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class AccountIdType implements UserType {

    private static final BankIdValueMapper mapper = new BankIdValueMapperImpl();

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.BIGINT};
    }

    @Override
    public Class returnedClass() {
        return AccountId.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return Objects.hashCode(x);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        return mapper.getValue(rs, names);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        mapper.setValue(st, (AccountId) value, index);
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        AccountId bankId = (AccountId) value;
        return AccountId.of(bankId.getBankId(), bankId.getId());
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    private interface BankIdValueMapper extends Serializable {
        AccountId getValue(ResultSet rs, String[] names) throws SQLException;

        void setValue(PreparedStatement st, AccountId value, int index) throws SQLException;

        String objectToSQLString(AccountId value);

        String toXMLString(AccountId value);

        AccountId fromXMLString(String xml);
    }

    private static class BankIdValueMapperImpl implements BankIdValueMapper {

        @Override
        public AccountId getValue(ResultSet rs, String[] names) throws SQLException {
            final Long value = rs.getLong(names[0]);
            if (rs.wasNull()) {
                return null;
            }
            return AccountId.fromIdWithBankId(value);
        }

        @Override
        public void setValue(PreparedStatement st, AccountId value, int index) throws SQLException {
            if (value == null) {
                st.setNull(index, Types.BIGINT);
            } else {
                st.setLong(index, value.get());
            }
        }

        @Override
        public String objectToSQLString(AccountId value) {
            return Objects.toString(value);
        }

        @Override
        public String toXMLString(AccountId value) {
            return Objects.toString(value);
        }

        @Override
        public AccountId fromXMLString(String xml) {
            return AccountId.fromString(xml);
        }
    }


}