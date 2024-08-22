package org.example.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.enums.OperationSymbol;
import org.example.exceptions.CalculatorException;

import java.util.Scanner;


public class Calculator {

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    public static void run(Scanner scanner, InputHandler inputHandler) {
        while (isApplicationRunning(scanner)) {
            double firstNumber = inputHandler.getNumber("a = ");
            String operationCharacter = inputHandler.getOperation();

            try {
                handleOperation(inputHandler, firstNumber, operationCharacter);
            } catch (CalculatorException e) {
                System.out.println(e.getMessage());
                logger.error("Calculator exception: {}", e.getMessage());
            }

            scanner.nextLine();
            System.out.println("\n------");
        }
    }

    private static boolean isApplicationRunning(Scanner scanner) {
        logger.info("Userul trebuie sa aleaga sa continua sau sa opreasca aplicatia");
        System.out.println("Daca vrei sa te opresti, scrie exit, altfel apasa orice tasta");
        String userInput = scanner.nextLine();
        if (userInput.equalsIgnoreCase("exit")) {
            logger.info("Aplicatia de calculator se inchide");
            return false;
        }
        return true;
    }

    private static void handleOperation(InputHandler inputHandler, double firstNumber, String operationCharacter) throws CalculatorException {
        boolean isUnaryOperation = OperationSymbol.isUnaryOperation(operationCharacter);

        if (isUnaryOperation) {
            double result = OperationManager.getUnaryOperationResult(firstNumber, operationCharacter);
            displayResult(result);
        } else {
            double secondNumber = inputHandler.getNumber("b = ");
            double result = OperationManager.getBinaryOperationResult(firstNumber, secondNumber, operationCharacter);
            displayResult(result);
        }
    }

    private static void displayResult(double result) {
        getResultInfo(result);
        System.out.println("Result: " + result);
    }

    private static void getResultInfo(double result) {
        logger.info("Rezultatul operatiei este: {}", result);
    }
}
