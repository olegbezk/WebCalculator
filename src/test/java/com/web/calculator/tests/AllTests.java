package com.web.calculator.tests;

/**
 * Created by oleg.bezkorovaynyi on 24 Jun 2016.
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CalculatorServiceTest.class, TransactionLogControllerTest.class})
public class AllTests {
}
