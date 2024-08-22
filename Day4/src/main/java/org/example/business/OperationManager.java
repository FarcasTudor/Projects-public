package org.example.business;

import org.example.enums.OperationSymbol;
import org.example.exceptions.CalculatorExceptions;
import org.example.exceptions.OperationNotFoundException;
import org.example.interfaces.Operation;
import org.example.operations.*;

public class OperationManager {
    public static Operation getBinaryOperation(double a, double b, String operation) throws OperationNotFoundException {
        return switch (OperationSymbol.valueOfOperation(operation)) {
            case ADDITION -> new Addition(a, b);
            case SUBTRACTION -> new Subtraction(a, b);
            case MULTIPLY -> new Multiplication(a, b);
            case DIVISION -> new Division(a, b);
            case MIN -> new Min(a, b);
            case MAX -> new Max(a, b);
            case POWER -> new PowerOf(a, b);
            default -> throw new OperationNotFoundException("Invalid operation: " + operation);
        };
    }

    public static Operation getUnaryOperation(double a, String operation) throws OperationNotFoundException {
        return switch (OperationSymbol.valueOfOperation(operation)) {
            case SQRT -> new Sqrt(a);
            case ROUND -> new Round(a);
            default -> throw new OperationNotFoundException("Invalid operation: " + operation);
        };
    }

    public static double getBinaryOperationResult(double a, double b, String operation) throws CalculatorExceptions {
        Operation binaryOperation = getBinaryOperation(a, b, operation);
        return binaryOperation.calculate();
    }

    public static double getUnaryOperationResult(double a, String operation) throws CalculatorExceptions {
        Operation unaryOperation = getUnaryOperation(a, operation);
        return unaryOperation.calculate();
    }
}

