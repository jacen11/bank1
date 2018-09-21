package com.example.bank.service.transfer.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.Map;

@Data
@ConfigurationProperties
public class BankProperties {

    private Integer id;
    private URI uri;
    private Map<String, String> headers;
}
