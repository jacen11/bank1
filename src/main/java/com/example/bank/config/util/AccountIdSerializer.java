package com.example.bank.config.util;

import com.example.bank.domain.AccountId;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class AccountIdSerializer extends JsonSerializer<AccountId> {

    @Override
    public void serialize(AccountId accountId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(accountId.get());
    }
}
