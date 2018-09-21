package com.example.bank.service.transfer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "services.transfer")
@Data
public class BanksProperties {

    private Map<String, BankProperties> bank;
}
