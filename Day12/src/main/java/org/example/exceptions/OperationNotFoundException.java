package org.example.exceptions;

public class OperationNotFoundException extends CalculatorException {
    public OperationNotFoundException(String message) {
        super(message);
    }
}
