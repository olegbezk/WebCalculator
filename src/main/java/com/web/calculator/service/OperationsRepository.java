package com.web.calculator.service;

import com.web.calculator.model.TransactionsLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@RepositoryRestResource
public interface OperationsRepository extends CrudRepository<TransactionsLog, Integer> {
    List<TransactionsLog> getFirstOperand(@Param("leftOperand") double firstOperand);
    List<TransactionsLog> getSecondOperand(@Param("rightOperand") double secondOperand);
    List<TransactionsLog> getResult(@Param("result") double result);
}