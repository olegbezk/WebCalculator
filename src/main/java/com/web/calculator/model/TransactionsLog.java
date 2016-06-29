package com.web.calculator.model;

import javax.persistence.*;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@Entity
@Table(name = "transactions_log")
public class TransactionsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int transactionId;

    @Column(name = "leftOperand")
    private double leftOperand;

    @Enumerated(EnumType.ORDINAL)
    public ArithmeticOperation arithmeticOperation;

    @Column(name = "rightOperand")
    private double rightOperand;

    @Column(name = "result")
    private double result;

    public int getTransactionId() { return transactionId; }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(double leftOperand) {
        this.leftOperand = leftOperand;
    }

    public double getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(double rightOperand) {
        this.rightOperand = rightOperand;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public ArithmeticOperation getArithmeticOperation() {
        return arithmeticOperation;
    }

    public void setArithmeticOperation(ArithmeticOperation arithmeticOperation) {
        this.arithmeticOperation = arithmeticOperation;
    }

    @Override
    public String toString() {
        return "TransactionsLog{" +
                "transactionId=" + transactionId +
                ", leftOperand=" + leftOperand +
                ", arithmeticOperation=" + arithmeticOperation +
                ", rightOperand=" + rightOperand +
                ", result=" + result +
                '}';
    }
}

