package org.example;

import org.example.business.Calculator;
import org.example.business.InputHandler;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        Calculator.run(scanner, inputHandler);
        scanner.close();
    }
}