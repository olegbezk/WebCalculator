package com.web.calculator.service;


import com.web.calculator.model.TransactionsLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

/**
 * Created by oleg.bezkorovaynyi on 22 Jun 2016.
 */

@Service
public class Calculator {

    public TransactionsLog calculate(TransactionsLog log) {
        double result;
        switch (log.getArithmeticOperation()) {
            case ADD:
                result = log.getLeftOperand() + log.getRightOperand();
                break;
            case SUBTRACT:
                result = log.getLeftOperand() - log.getRightOperand();
                break;
            case MULTIPLE:
                result = log.getLeftOperand() * log.getRightOperand();
                break;
            case DIVIDE:
                if (log.getRightOperand() != 0.0) {
                    result = log.getLeftOperand() / log.getRightOperand();
                } else {
                    throw new UnexpectedRollbackException("Division by zero");
                }
                break;
            case POWER:
                result = Math.pow(log.getLeftOperand(), log.getRightOperand());
                break;
            default:
                throw new UnexpectedRollbackException("Unsupported arithmetic operation");
        }
        log.setResult(result);
        return log;
    }
}