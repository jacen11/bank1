package com.example.bank.entity;

import com.example.bank.domain.AccountId;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "accounts")
@Getter
@Setter
@NamedEntityGraph(name = "abc", includeAllAttributes = true)
public class BankAccount {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id", unique = true, nullable = false)
    @Type(type = "com.example.bank.entity.type.AccountIdType")
    private AccountId id;

    private BigDecimal balance;

    @Column(name = "name_account", nullable = false)
    private String nameAccount;


    @OneToMany(mappedBy = "accountFrom", cascade = CascadeType.ALL)
    private Set<AccountTransaction> fromTransactions;

    @OneToMany(mappedBy = "accountTo", cascade = CascadeType.ALL)
    private Set<AccountTransaction> toTransactions;

    public List<AccountTransaction> getTransactions() {
        List<AccountTransaction> transactions = new ArrayList<>();
        if (fromTransactions != null) {
            transactions.addAll(fromTransactions);
        }
        if (toTransactions != null) {
            transactions.addAll(toTransactions);
        }
        return transactions;
    }

    //    public BankAccount() {
//    }
}
