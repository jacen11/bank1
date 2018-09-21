package com.example.bank.service.transfer;

import com.example.bank.domain.AccountId;
import com.example.bank.domain.Transfer;
import com.example.bank.repostory.CustomerRepository;
import com.example.bank.service.transfer.exception.TooManyBanksTransferException;
import com.example.bank.service.transfer.exception.UnknownBankTransferException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Service
@Primary
public class CompositeTransferService implements TransferService {

    private final CustomerRepository customerRepository;

    private final TransferService internalCustomerService;

    private final Collection<ExternalTransferService> externalCustomerServices;


    public CompositeTransferService(
            CustomerRepository customerRepository,
            @Qualifier(INTERNAL_CUSTOMER_SERVICE) TransferService internalCustomerService,
            Collection<ExternalTransferService> externalCustomerServices
    ) {
        this.customerRepository = customerRepository;
        this.internalCustomerService = internalCustomerService;
        this.externalCustomerServices = externalCustomerServices;
    }

    /**
     * Если получатель является клиентом нашего банка, то переводим деньги внутри собственной системы.
     * <p>Иначе, переводим во внешние банки
     *
     * @param transfer клиент-отправитель
     */
    @Override
    public void transfer(final Transfer transfer) {
        final AccountId fromAccountId = transfer.getFromAccount();
        if (transfer.getToAccount().isInternal()) {
            internalCustomerService.transfer(transfer);
            return;
        }
        Integer otherBankId = transfer.getToAccount().getBankId();
        externalCustomerServices
                .stream()
                .filter(externalCustomerService -> externalCustomerService.getAvaliableBanksId().contains(otherBankId))
                .collect(Collectors.collectingAndThen(toSet(), (Set<ExternalTransferService> services) -> {
                    if (services.isEmpty()) {
                        throw new UnknownBankTransferException(otherBankId);
                    }
                    if (services.size() > 1) {
                        throw new TooManyBanksTransferException();
                    }
                    return services.iterator().next();
                })).transfer(transfer);
    }
}
