package com.example.bank.service.report;

import com.example.bank.entity.AccountTransaction;
import com.example.bank.entity.type.AccountId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
public class JsonReportGenerator implements ReportGenerator {

    private final ObjectMapper mapper;

    public JsonReportGenerator(ObjectMapper mapper) {
        this.mapper = requireNonNull(mapper);
    }

    @Override
    public Resource generate(LocalDate from, LocalDate to, AccountId accountId) {
        List<AccountTransaction> transactionList = new ArrayList<>();//поиск транзакций
        try {
            byte[] bytes = mapper.writeValueAsBytes(transactionList);
            return new ByteArrayResource(bytes);
        } catch (JsonProcessingException e) {
            throw new ReportException(e.getMessage(), e);
        }
    }
}
