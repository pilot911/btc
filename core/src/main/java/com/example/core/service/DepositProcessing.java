package com.example.core.service;

import com.example.common.DbConstants;
import com.example.core.dao.TransactionHistoryDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Positive;

@Service
@AllArgsConstructor
public class DepositProcessing {

    private TransactionHistoryDao transactionHistoryDao;

    @Transactional(value = DbConstants.TRANSACTION_MANAGER)
    public void income(@Positive double btcSum) {
        double sum = btcSum * 0.000_000_001;




    }

    public void outcome(@Positive double btcSum) {

    }
}
