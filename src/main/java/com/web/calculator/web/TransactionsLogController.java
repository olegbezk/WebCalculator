package com.web.calculator.web;

import java.util.List;

import com.web.calculator.model.TransactionsLog;
import com.web.calculator.dao.TransactionLogRepository;
import com.web.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@RestController
@RequestMapping(value = "/transaction-log")
public class TransactionsLogController {

    @Autowired
    private TransactionLogRepository transactionLogDao;

    @Autowired
    private CalculatorService calculatorService;

    @Transactional
    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    public List<TransactionsLog> getTransactionLogs() {
        return transactionLogDao.findAll();
    }

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionsLog addTransactionLog(@RequestBody TransactionsLog transactionLog) {
        calculatorService.calculate(transactionLog);
        return transactionLogDao.save(transactionLog);
    }

    @Transactional
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteTransactionalLog(@RequestBody   TransactionsLog transactionsLog) {
        transactionLogDao.delete(transactionsLog.getTransactionId());
    }
}
