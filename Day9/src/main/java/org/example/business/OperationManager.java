package org.example.business;

import org.example.enums.OperationSymbol;
import org.example.exceptions.CalculatorExceptions;
import org.example.exceptions.OperationNotFoundException;
import org.example.validations.Validations;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperationManager {
    public static BinaryOperator<Double> getBinaryOperation(String operation) throws OperationNotFoundException {
        return switch (OperationSymbol.valueOfOperation(operation)) {
            case ADDITION -> Double::sum;
            case SUBTRACTION -> (a, b) -> a - b;
            case MULTIPLY -> (a, b) -> a * b;
            case DIVISION -> OperationManager::getDivisionOperation;
            case MIN -> Double::min;
            case MAX -> Double::max;
            case POWER -> OperationManager::getPowerOfOperation;
            default -> throw new OperationNotFoundException("Invalid operation: " + operation);
        };
    }

    public static UnaryOperator<Double> getUnaryOperation(String operation) throws OperationNotFoundException {
        return switch (OperationSymbol.valueOfOperation(operation)) {
            case SQRT -> OperationManager::getSqrtOperation;
            case ROUND -> (a) -> (double) Math.round(a);
            default -> throw new OperationNotFoundException("Invalid operation: " + operation);
        };
    }

    private static Double getSqrtOperation(Double operand) {
        Validations.validateSqrt(operand);
        return Math.sqrt(operand);
    }

    public static double getBinaryOperationResult(double a, double b, String operation) throws CalculatorExceptions {
        BinaryOperator<Double> binaryOperation = getBinaryOperation(operation);
        return binaryOperation.apply(a, b);
    }

    public static double getUnaryOperationResult(double a, String operation) throws CalculatorExceptions {
        UnaryOperator<Double> unaryOperation = getUnaryOperation(operation);
        return unaryOperation.apply(a);
    }

    private static Double getPowerOfOperation(Double operand1, Double operand2) {
        Validations.validatePower(operand1, operand2);
        return Math.pow(operand1, operand2);
    }

    private static Double getDivisionOperation(Double operand1, Double operand2) {
        Validations.validateDivision(operand2);
        return operand1 / operand2;
    }
}

