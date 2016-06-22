
CREATE TABLE arithmetic_ops (
id INT NOT NULL AUTO_INCREMENT,
operation CHAR(10) NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE transactions_log (
id INT NOT NULL AUTO_INCREMENT,
leftOperand DOUBLE NOT NULL,
operator INT NOT NULL,
rightOperand DOUBLE NOT NULL,
result DOUBLE NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (operator) REFERENCES arithmetic_ops(id)
);