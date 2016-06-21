package com.web.calculator.model;

/**
 * Created by oleg.bezkorovaynyi on 21 Jun 2016.
 */

public enum ArithmeticOperation {

    ADD(1), SUBTRACT(2), MULTIPLE(3), DIVIDE(4), POWER(5);

    private int number;

    ArithmeticOperation(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
