package com.web.calculator.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.calculator.Application;
import com.web.calculator.dao.TransactionLogRepository;
import com.web.calculator.model.ArithmeticOperation;
import com.web.calculator.model.TransactionsLog;
import org.junit.After;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

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
    public void testCreateOperationLogApi() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("leftOperand", 4);
        requestBody.put("rightOperand", 2);
        requestBody.put("arithmeticOperation", ArithmeticOperation.ADD);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> httpEntity =
                new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        Map<String, Object> apiResponse =
                restTemplate.postForObject("http://localhost:8080/transaction-log/add/",
                        httpEntity, Map.class, Collections.EMPTY_MAP);

        assertNotNull(apiResponse);

        String message = apiResponse.get("message").toString();

        assertEquals("Don't know", message);

        String transactionId = ((Map<String, Object>) apiResponse
                .get("transactionId")).get("id").toString();

        assertNotNull(transactionId);

        List<TransactionsLog> transactionsLog = transactionLogRepository.findAll();

        for (TransactionsLog log : transactionsLog) {
            assertEquals("transactionId", log.getTransactionId());
            assertEquals("leftOperand", log.getLeftOperand());
            assertEquals("arithmeticOperation", log.getArithmeticOperation());
            assertEquals("rightOperand", log.getRightOperand());
            assertEquals("result", log.getResult());
        }

        transactionLogRepository.delete(transactionsLog);
    }

}
