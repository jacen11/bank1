package com.example.bank.service.transfer.external;

import com.example.bank.domain.Transfer;
import com.example.bank.domain.TransferResponse;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.service.transfer.ExternalTransferService;
import com.example.bank.service.transfer.configuration.BankProperties;
import com.example.bank.service.transfer.configuration.BanksProperties;
import com.example.bank.service.transfer.exception.ExternalTransferException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.http.RequestEntity.post;

/**
 * Отправка по умолчанию в другой банк (информация берется из файла application.properties)
 */
@Service
public class DefaultExternalTransferService implements ExternalTransferService {

    private final RestOperations rest;

    private final Map<Integer, BankProperties> banksProperties;

    private final BankAccountRepository bankAccountRepository;


    public DefaultExternalTransferService(RestOperations rest, BanksProperties banksProperties, BankAccountRepository bankAccountRepository) {
        this.rest = rest;
        this.bankAccountRepository = bankAccountRepository;
        Map<String, BankProperties> bank = banksProperties.getBank();
        Map<Integer, BankProperties> buffer = new HashMap<>();
        bank.forEach((bankName, bankProperties) -> buffer.put(bankProperties.getId(), bankProperties));
        this.banksProperties = Collections.unmodifiableMap(buffer);
    }


    @Override
    @Transactional
    public void transfer(Transfer transfer) {
        Integer externalBankId = transfer.getToAccount().getBankId();
        BankProperties bankProperties = banksProperties.get(externalBankId);
        RequestEntity.BodyBuilder requestBuilder = post(bankProperties.getUri());
        bankProperties.getHeaders().forEach(requestBuilder::header);
        RequestEntity<Transfer> requestEntity = requestBuilder
                .body(transfer);
        ResponseEntity<TransferResponse> response = rest.exchange(requestEntity, TransferResponse.class);
        if (response.getStatusCode().is4xxClientError()) {
            throw new ExternalTransferException();
        }

    }

    @Override
    public Set<Integer> getAvaliableBanksId() {
        return banksProperties.keySet();
    }
}
