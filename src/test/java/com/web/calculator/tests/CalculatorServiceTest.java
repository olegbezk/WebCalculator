package com.web.calculator.tests;

import com.web.calculator.model.ArithmeticOperation;
import com.web.calculator.model.TransactionsLog;
import com.web.calculator.service.CalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oleg.bezkorovaynyi on 23 Jun 2016.
 */

public class CalculatorServiceTest {

    private final double THRESHOLD = 0.000001;
    private TransactionsLog transactionsLog;
    private Map<ArithmeticOperation, Double> expectedResults;
    private CalculatorService calculatorService;

    @Before
    public void init() {
        calculatorService = new CalculatorService();
        expectedResults = new HashMap<>();
        transactionsLog = new TransactionsLog();
        transactionsLog.setLeftOperand(4.0);
        transactionsLog.setRightOperand(4.0);
        expectedResults.put(ArithmeticOperation.ADD, 8.0);
        expectedResults.put(ArithmeticOperation.SUBTRACT, 0.0);
        expectedResults.put(ArithmeticOperation.MULTIPLY, 16.0);
        expectedResults.put(ArithmeticOperation.DIVIDE, 1.0);
        expectedResults.put(ArithmeticOperation.POWER, 256.0);
    }

    @Test
    public void calculatorTest() {
        for (ArithmeticOperation operation : ArithmeticOperation.values()) {
            transactionsLog.setArithmeticOperation(operation);
            double result = calculatorService.calculate(transactionsLog).getResult();
            Assert.assertEquals("Verify arithmetic operations results",
                    expectedResults.get(operation), result, THRESHOLD);
        }
    }

    @Test(expected = ArithmeticException.class)
    public void divideByZeroExceptionTest() {
            transactionsLog.setLeftOperand(4.0);
            transactionsLog.setRightOperand(0.0);
            transactionsLog.setArithmeticOperation(ArithmeticOperation.DIVIDE);
            calculatorService.calculate(transactionsLog).getResult();
    }
}
