package com.example.bank.service.report;

import com.example.bank.entity.type.AccountId;
import org.springframework.core.io.Resource;

import java.time.LocalDate;

public interface ReportGenerator {

    Resource generate(LocalDate from, LocalDate to, AccountId accountId);
}
