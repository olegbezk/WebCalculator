package com.web.calculator.dao;

import com.web.calculator.model.TransactionsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@Repository
public interface TransactionLogDao extends JpaRepository<TransactionsLog, Integer> {
//    List<TransactionsLog> getFirstOperand(@Param("leftOperand") double firstOperand);
//    List<TransactionsLog> getSecondOperand(@Param("rightOperand") double secondOperand);
//    List<TransactionsLog> getResult(@Param("result") double result);
}