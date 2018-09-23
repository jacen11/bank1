package com.example.bank.repostory;

import com.example.bank.entity.type.AccountId;
import com.example.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CustomerRepository extends RefreshableJpaRepository<Customer, Long>, UserDetailsService {

    @Override
    default Customer loadUserByUsername(String s) throws UsernameNotFoundException {
        return findByUsername(s);
    }

    @Query("select c from #{#entityName} c left join fetch c.accounts where c.username=:username")
    Customer findByUsername(@Param("username") String username);



    @Query("select count(c) > 0 " +
            "from #{#entityName} c inner join c.accounts a " +
            "where a.id = :accountId " +
            "  and c.accountNotExpired = true and c.accountNotLocked = true and c.enabled = true")
    boolean availableByAccountId(@Param("accountId") AccountId accountId);

}
