package org.example.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.enums.OperationSymbol;
import org.example.exceptions.CalculatorException;
import org.example.exceptions.OperationNotFoundException;
import org.example.validations.Validations;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperationManager {
    private final static Logger logger = LogManager.getLogger(OperationManager.class);

    public static BinaryOperator<Double> getBinaryOperator(String operation) throws OperationNotFoundException {
        logger.debug("Getting binary operation for: {}", operation);
        return switch (OperationSymbol.valueOfOperation(operation)) {
            case ADDITION -> Double::sum;
            case SUBTRACTION -> (a, b) -> a - b;
            case MULTIPLY -> (a, b) -> a * b;
            case DIVISION -> OperationManager::getDivisionOperation;
            case MIN -> Double::min;
            case MAX -> Double::max;
            case POWER -> OperationManager::getPowerOfOperation;
            default -> {
                logger.error("Throwing exception for invalid binary operation: {}", operation);
                throw new OperationNotFoundException("Invalid operation: " + operation);
            }
        };
    }

    public static UnaryOperator<Double> getUnaryOperator(String operation) throws OperationNotFoundException {
        logger.debug("Getting unary operation for: {}", operation);
        return switch (OperationSymbol.valueOfOperation(operation)) {
            case SQRT -> OperationManager::getSqrtOperation;
            case ROUND -> (a) -> (double) Math.round(a);
            default -> {
                logger.error("Throwing exception for invalid unary operation: {}", operation);
                throw new OperationNotFoundException("Invalid operation: " + operation);
            }
        };
    }

    public static double getBinaryOperationResult(double a, double b, String operation) throws CalculatorException {
        logger.debug("Calculating binary operation for: {} {} {}", a, operation, b);
        BinaryOperator<Double> binaryOperation = getBinaryOperator(operation);
        return binaryOperation.apply(a, b);
    }

    public static double getUnaryOperationResult(double a, String operation) throws CalculatorException {
        logger.debug("Calculating unary operation for: {} {}", operation, a);
        UnaryOperator<Double> unaryOperation = getUnaryOperator(operation);
        return unaryOperation.apply(a);
    }

    private static Double getPowerOfOperation(Double operand1, Double operand2) {
        logger.debug("Validating and retrieving power operation for: {} {}", operand1, operand2);
        Validations.validatePower(operand1, operand2);
        return Math.pow(operand1, operand2);
    }

    private static Double getDivisionOperation(Double operand1, Double operand2) {
        logger.debug("Validating and retrieving division operation for: {} {}", operand1, operand2);
        Validations.validateDivision(operand2);
        return operand1 / operand2;
    }

    private static Double getSqrtOperation(Double operand) {
        logger.debug("Validating and retrieving sqrt operation for: {}", operand);
        Validations.validateSqrt(operand);
        return Math.sqrt(operand);
    }
}

