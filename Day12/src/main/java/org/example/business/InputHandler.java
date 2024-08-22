package org.example.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.enums.OperationSymbol;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private static final Logger logger = LogManager.getLogger(InputHandler.class);
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public double getNumber(String text) {
        while (true) {
            try {
                System.out.print(text);
                double number = scanner.nextDouble();
                logger.debug("User input for {}: {}", text, number);
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Nu ai scris un numar valid!");
                logger.error("Invalid number input!");
                scanner.next();
            }
        }
    }

    public String getOperation() {
        while (true) {
            System.out.print("Selecteaza operatia (+, -, *, /, min, max, ^, round, sqrt) : ");
            String operation = scanner.next();
            if (OperationSymbol.isValid(operation)) {
                logger.debug("Operation input: {}", operation);
                return operation;
            } else {
                logger.error("Operatie invalida: {}", operation);
                System.out.println("Nu ai scris o operatie valida!");
            }
        }
    }
}
