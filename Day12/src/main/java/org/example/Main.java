package org.example;

import org.example.business.Calculator;
import org.example.business.InputHandler;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting the calculator application");

        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        Calculator.run(scanner, inputHandler);
        scanner.close();

        logger.info("Closing the calculator application");
    }
}