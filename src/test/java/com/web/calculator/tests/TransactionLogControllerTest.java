package com.web.calculator.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.calculator.Application;
import com.web.calculator.dao.TransactionLogRepository;
import com.web.calculator.model.ArithmeticOperation;
import com.web.calculator.model.TransactionsLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static junit.framework.TestCase.*;

/**
 * Created by oleg.bezkorovaynyi on 27 Jun 2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class TransactionLogControllerTest {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private TransactionLogRepository transactionLogRepository;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testAddOperationTransactionLogApi() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("leftOperand", 4);
        requestBody.put("rightOperand", 2);
        requestBody.put("arithmeticOperation", ArithmeticOperation.ADD);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        Map<String, Object> apiResponse = restTemplate.postForObject("http://localhost:8080/transaction-log/add/",
                        httpEntity, Map.class, Collections.EMPTY_MAP);

        assertNotNull(apiResponse);

        String transactionId = apiResponse.get("transactionId").toString();

        assertNotNull(transactionId);

        List<TransactionsLog> transactionsLog = transactionLogRepository.findAll();

        for (TransactionsLog log : transactionsLog) {
            assertEquals(apiResponse.get("transactionId"), log.getTransactionId());
            assertEquals(apiResponse.get("leftOperand"), log.getLeftOperand());
            assertEquals(ArithmeticOperation.ADD, log.getArithmeticOperation());
            assertEquals(apiResponse.get("rightOperand"), log.getRightOperand());
            assertEquals(apiResponse.get("result"), log.getResult());
        }

        transactionLogRepository.delete(transactionsLog.get(0).getTransactionId());
    }

    @Test
    public void testGetOperationTransactionLogApi() {
        TransactionsLog transactionsLog1 = new TransactionsLog();
        transactionsLog1.setLeftOperand(4.0);
        transactionsLog1.setRightOperand(6.0);
        transactionsLog1.setArithmeticOperation(ArithmeticOperation.MULTIPLY);
        transactionLogRepository.save(transactionsLog1);

        TransactionsLog transactionsLog2 = new TransactionsLog();
        transactionsLog2.setLeftOperand(5.0);
        transactionsLog2.setRightOperand(3.0);
        transactionsLog2.setArithmeticOperation(ArithmeticOperation.DIVIDE);
        transactionLogRepository.save(transactionsLog2);

        TransactionsLog[] transactionsLogs = restTemplate
                .getForObject("http://localhost:8080/transaction-log/logs/", TransactionsLog[].class);

        assertTrue(transactionsLogs.length == 2);

        assertEquals(ArithmeticOperation.MULTIPLY, transactionsLogs[0].getArithmeticOperation());
        assertEquals(ArithmeticOperation.DIVIDE, transactionsLogs[1].getArithmeticOperation());

        transactionLogRepository.delete(transactionsLog1.getTransactionId());
        transactionLogRepository.delete(transactionsLog2.getTransactionId());
    }

    @Test
    public void testDeleteOperationTransactionLogApi() {

        TransactionsLog transactionsLog = new TransactionsLog();
        transactionsLog.setLeftOperand(4.0);
        transactionsLog.setRightOperand(6.0);
        transactionsLog.setArithmeticOperation(ArithmeticOperation.MULTIPLY);
        transactionLogRepository.save(transactionsLog);

        List<TransactionsLog> transactionsLogList = transactionLogRepository.findAll();
        int id = transactionsLogList.get(0).getTransactionId();

        assertNotNull(id);

        restTemplate.delete("http://localhost:8080/transaction-log/delete/" + id, Collections.EMPTY_MAP);

        List<TransactionsLog> transactionsLogFromDBAfterDelOp = transactionLogRepository.findAll();

        assertEquals(0, transactionsLogFromDBAfterDelOp.size());
    }

}
