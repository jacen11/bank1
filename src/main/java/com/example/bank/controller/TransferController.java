package com.example.bank.controller;

import com.example.bank.domain.Transfer;
import com.example.bank.entity.Customer;
import com.example.bank.service.transfer.TransferService;
import com.example.bank.service.transfer.exception.TransferException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity transfer(@AuthenticationPrincipal Customer customer, @RequestBody Transfer transfer) {
        transferService.transfer(transfer);
        return ok().build();
    }

    @ExceptionHandler(TransferException.class)
    @ResponseStatus(BAD_REQUEST)
    public String exceptionHandler(TransferException ex) {
        return "error";
    }
}
