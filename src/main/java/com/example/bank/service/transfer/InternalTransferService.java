package com.example.bank.service.transfer;

import com.example.bank.domain.Transfer;
import com.example.bank.entity.BankAccount;
import com.example.bank.repostory.BankAccountRepository;
import com.example.bank.repostory.CustomerRepository;
import com.example.bank.service.transfer.exception.AccountNotFoundTransferException;
import com.example.bank.service.transfer.exception.CustomerNotAvailableTransferException;
import com.example.bank.service.transfer.exception.NotEnoughCashTranferException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.math.BigDecimal;

import static com.example.bank.service.transfer.TransferService.INTERNAL_CUSTOMER_SERVICE;
import static java.util.Objects.requireNonNull;

@Service(INTERNAL_CUSTOMER_SERVICE)
public class InternalTransferService implements TransferService {


    private final BankAccountRepository bankAccountRepository;

    private final CustomerRepository customerRepository;

    public InternalTransferService(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = requireNonNull(bankAccountRepository);
        this.customerRepository = requireNonNull(customerRepository);
    }

    @Transactional
    @Override
    public void transfer(Transfer transfer) {
        BankAccount fromAccount = bankAccountRepository
                .findById(transfer.getFromAccount())
                .orElseThrow(() -> new AccountNotFoundTransferException(transfer.getFromAccount()));
        BankAccount toAccount = bankAccountRepository
                .findById(transfer.getToAccount())
                .orElseThrow(() -> new AccountNotFoundTransferException(transfer.getToAccount()));

        BigDecimal fromBalance = fromAccount.getBalance();
        if (fromBalance.subtract(transfer.getAmount()).compareTo(BigDecimal.ZERO) < 1) {
            throw new NotEnoughCashTranferException();
        }
        if (!customerRepository.availableByAccountId(transfer.getFromAccount())) {
            throw new CustomerNotAvailableTransferException(transfer.getFromAccount());
        }
        if (!customerRepository.availableByAccountId(transfer.getToAccount())) {
            throw new CustomerNotAvailableTransferException(transfer.getToAccount());
        }
        fromAccount.setBalance(fromBalance.subtract(transfer.getAmount()));
        bankAccountRepository.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance().add(transfer.getAmount()));
        bankAccountRepository.save(toAccount);
    }
}
