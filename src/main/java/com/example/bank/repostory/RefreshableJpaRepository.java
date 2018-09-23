package com.example.bank.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface RefreshableJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    T refresh(T entity);
}
