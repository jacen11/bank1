package com.example.bank.config.util;

import com.example.bank.domain.AccountId;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class AccountIdDeserializer extends JsonDeserializer<AccountId> {

    @Override
    public AccountId deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        long rawValue = jsonParser.getLongValue();
        return AccountId.fromIdWithBankId(rawValue);
    }


}
