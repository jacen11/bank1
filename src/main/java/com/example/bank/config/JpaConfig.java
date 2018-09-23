package com.example.bank.config;

import com.example.bank.repostory.RefreshableJpaRepository;
import com.example.bank.repostory.SimpleRefreshableJpaRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = RefreshableJpaRepository.class, repositoryBaseClass = SimpleRefreshableJpaRepository.class)
public class JpaConfig {
}
