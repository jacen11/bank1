package com.example.bank.entity;

import com.example.bank.domain.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter @Setter
public class Customer implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_not_expired")
    private boolean accountNotExpired = true;
    @Column(name = "account_not_locked")
    private boolean accountNotLocked = true;
    @Column(name = "enabled")
    private boolean enabled = true;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "customer_id")
    private List<BankAccount> accounts;

//    @Column(name = "cash", nullable = true)
//    private BigDecimal cash;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                '}';
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
