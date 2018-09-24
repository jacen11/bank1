package com.example.bank.service.transfer;

import com.example.bank.domain.Transfer;
import com.example.bank.domain.TransferResponse;
import com.example.bank.service.transfer.exception.ExternalTransferException;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

@Service
public class TwoExternalTransferService implements ExternalTransferService {

    private final RestOperations rest;

    public TwoExternalTransferService(RestOperations rest) {
        this.rest = rest;
    }

    @Override
    public Set<Integer> getAvaliableBanksId() {
        // FIXME: 23.09.2018
        return Collections.singleton(99);
    }

    @Override
    public void transfer(Transfer transfer) {
        URI uri = UriComponentsBuilder.fromHttpUrl("https://ec2-18-223-2-140.us-east-2.compute.amazonaws.com:8443")
                .queryParam("passport", transfer.getToAccount().get())
                .queryParam("amount", transfer.getAmount())
                .queryParam("bank_id", getAvaliableBanksId().iterator().next())
                .build()
                .toUri();
        ResponseEntity<TransferResponse> response = rest.exchange(RequestEntity.post(uri).build(), TransferResponse.class);
        if (response.getStatusCode().is4xxClientError()) {
            throw new ExternalTransferException();
        }
    }
}
