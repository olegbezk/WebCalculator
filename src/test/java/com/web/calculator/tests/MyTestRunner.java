package com.web.calculator.tests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Created by oleg.bezkorovaynyi on 24 Jun 2016.
 */

public class MyTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CalculatorServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}
