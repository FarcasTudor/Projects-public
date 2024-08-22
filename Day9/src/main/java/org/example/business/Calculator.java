package org.example.business;

import org.example.enums.OperationSymbol;
import org.example.exceptions.CalculatorExceptions;

import java.util.Scanner;


public class Calculator {
    public static void run(Scanner scanner, InputHandler inputHandler)  {
        while (true) {
            System.out.println("Daca vrei sa te opresti, scrie exit, altfel apasa orice tasta");
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            double firstNumber = inputHandler.getNumber("a = ");
            String operationCharacter = inputHandler.getOperation();
            boolean isUnaryOperation = OperationSymbol.isUnaryOperation(operationCharacter);

            try {
                if (isUnaryOperation) {
                    System.out.println("Result: " + OperationManager.getUnaryOperationResult(firstNumber, operationCharacter));
                } else {
                    double secondNumber = inputHandler.getNumber("b = ");
                    System.out.println("Result: " + OperationManager.getBinaryOperationResult(firstNumber, secondNumber, operationCharacter));
                }
            } catch (CalculatorExceptions e) {
                System.out.println(e.getMessage());
            }
            scanner.nextLine();
            System.out.println("\n------");
        }
    }
}
