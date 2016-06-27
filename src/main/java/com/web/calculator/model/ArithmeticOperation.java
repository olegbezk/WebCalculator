package com.web.calculator.model;

/**
 * Created by oleg.bezkorovaynyi on 21 Jun 2016.
 */

public enum ArithmeticOperation {

    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POWER("^");

    private String value;

    ArithmeticOperation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
