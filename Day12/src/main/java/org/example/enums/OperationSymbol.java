package org.example.enums;

import org.example.exceptions.OperationNotFoundException;

import java.util.HashMap;
import java.util.Map;

public enum OperationSymbol {
    ADDITION("+", false),
    SUBTRACTION("-", false),
    MULTIPLY("*", false),
    DIVISION("/", false),
    POWER("^", false),
    MIN("min", false),
    MAX("max", false),
    ROUND("round", true),
    SQRT("sqrt", true);

    private final String operation;
    private final boolean isUnary;
    private static final Map<String, OperationSymbol> operationMap = new HashMap<>();

    OperationSymbol(String operation, boolean unary) {
        this.operation = operation;
        this.isUnary = unary;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isUnary() {
        return isUnary;
    }

    static {
        for (OperationSymbol operation : values()) {
            operationMap.put(operation.getOperation(), operation);
        }
    }

    public static boolean isValid(String symbol) {
        return operationMap.containsKey(symbol);
    }

    public static boolean isUnaryOperation(String symbol) {
        return operationMap.get(symbol).isUnary();
    }

    public static OperationSymbol valueOfOperation(String symbol) throws OperationNotFoundException {
        OperationSymbol operationSymbol = operationMap.get(symbol);
        if (operationSymbol == null) {
            throw new OperationNotFoundException("No enum constant for symbol: " + symbol);
        }
        return operationSymbol;
    }
}
