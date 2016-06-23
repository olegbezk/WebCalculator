package com.web.calculator.dao;

import com.web.calculator.model.TransactionsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@Repository
public interface TransactionLogDao extends JpaRepository<TransactionsLog, Integer> {
}