package com.web.calculator.service;

import com.web.calculator.model.ArithmeticOperation;
import com.web.calculator.model.TransactionsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by oleg.bezkorovaynyi on 27 Jun 2016.
 */

@Service
public class TransactionLogService {

    @Autowired
    private TransactionsLog transactionsLog;

    public void setTransactionsLog(TransactionsLog transactionsLog) {
        switch (transactionsLog.getArithmeticOperation()) {
            case ADD:
                transactionsLog.setArithmeticOperation(ArithmeticOperation.ADD);
        }
    }
}
