package com.example.bank.repostory;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

@NoRepositoryBean
public class SimpleRefreshableJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T,ID>
        implements RefreshableJpaRepository<T, ID> {

    private final EntityManager em;

    public SimpleRefreshableJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    public SimpleRefreshableJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    @Transactional
    public T refresh(T entity) {
        em.refresh(entity);
        return entity;
    }

}
