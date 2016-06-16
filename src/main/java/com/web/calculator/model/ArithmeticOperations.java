package com.web.calculator.model;

import javax.persistence.*;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

@Entity
@Table(name = "arithmetic_ops")
public class ArithmeticOperations {

    public ArithmeticOperations() {
    }

    public ArithmeticOperations(int id, String operation) {
        this.id = id;
        this.operation = operation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "operation", unique = true,
            nullable = false, length = 10)
    private String operation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "ArithmeticOperations{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                '}';
    }
}
