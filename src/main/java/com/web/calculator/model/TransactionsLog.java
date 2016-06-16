package com.web.calculator.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@Entity
@Table(name = "transactions_log")
public class TransactionsLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "leftOperand")
    private double leftOperand;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ArithmeticOperations> operator;

    @Column(name = "rightOperand")
    private double rightOperand;

    @Column(name = "result")
    private double result;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(double leftOperand) {
        this.leftOperand = leftOperand;
    }

    public List<ArithmeticOperations> getOperator() {
        return operator;
    }

    public void setOperator(List<ArithmeticOperations> operator) {
        this.operator = operator;
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

    @Override
    public String toString() {
        return "TransactionsLog{" +
                "transactionId=" + transactionId +
                ", leftOperand=" + leftOperand +
                ", operator=" + operator +
                ", rightOperand=" + rightOperand +
                ", result=" + result +
                '}';
    }
}

