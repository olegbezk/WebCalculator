CREATE DATABASE IF NOT EXISTS calculator_data;

CREATE TABLE arithmetic_ops (
id INT NOT NULL AUTO_INCREMENT,
operation CHAR(10) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE transactions_log (
transactionId INT NOT NULL AUTO_INCREMENT,
leftOperand DOUBLE NOT NULL,
operator REFERENCES arithmetic_ops(id),
rightOperand DOUBLE NOT NULL,
result DOUBLE NOT NULL,
PRIMARY KEY id
);