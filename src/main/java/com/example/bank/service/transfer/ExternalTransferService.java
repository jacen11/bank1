package com.example.bank.service.transfer;

import java.util.Set;

public interface ExternalTransferService extends TransferService {

    Set<Integer> getAvaliableBanksId();
}
