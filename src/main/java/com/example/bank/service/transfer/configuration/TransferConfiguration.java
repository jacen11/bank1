package com.example.bank.service.transfer.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(BanksProperties.class)
public class TransferConfiguration {

    @Bean
    public RestOperations rest() {
        return new RestTemplate();
    }
}
