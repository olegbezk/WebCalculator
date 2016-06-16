package com.web.calculator;

import org.flywaydb.core.Flyway;

/**
 * Created by oleg.bezkorovaynyi on 17 Jun 2016.
 */

public class App {
    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        flyway.migrate();
    }
}
