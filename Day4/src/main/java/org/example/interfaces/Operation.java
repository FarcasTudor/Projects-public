package org.example.interfaces;

import org.example.exceptions.CalculatorExceptions;

public interface Operation {
    double calculate() throws CalculatorExceptions;
}
