package org.example.exceptions;

public class DivisionByZeroException extends CalculatorException {
    public DivisionByZeroException(String message) {
        super(message);
    }
}
