package org.example.business;

import org.example.enums.OperationSymbol;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public double getNumber(String text) {
        while (true) {
            try {
                System.out.print(text);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Nu ai scris un numar valid!");
                scanner.next();
            }
        }
    }

    public String getOperation() {
        while (true) {
            System.out.print("Selecteaza operatia (+, -, *, /, min, max, ^, round, sqrt) : ");
            String operation = scanner.next();
            if (OperationSymbol.isValid(operation)) {
                return operation;
            } else {
                System.out.println("Nu ai scris o operatie valida!");
            }
        }
    }
}
