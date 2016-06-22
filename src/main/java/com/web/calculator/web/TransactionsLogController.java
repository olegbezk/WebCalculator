package com.web.calculator.web;

import java.util.List;

import com.web.calculator.model.TransactionsLog;
import com.web.calculator.dao.TransactionLogDao;
import com.web.calculator.service.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@RestController
@RequestMapping(value = "/transaction-log")
public class TransactionsLogController {
    @Autowired
    private TransactionLogDao transactionLogDao;

    @Autowired
    private Calculator calculator;

    @RequestMapping(value = "/logs", method = RequestMethod.GET)
    @Transactional
    public List<TransactionsLog> getTransactionLogs() {
        return transactionLogDao.findAll();
    }

    @Transactional
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionsLog addTransactionLog(@RequestBody TransactionsLog transactionLog) {
        return transactionLogDao.save(transactionLog);
    }
}
