package org.example.operations;

import org.example.exceptions.NegativeNumberExceptions;

public class Sqrt extends UnaryOperation {

    public Sqrt(double operand) {
        super(operand);
    }

    @Override
    public double calculate() throws NegativeNumberExceptions {
        if (operand < 0) {
            throw new NegativeNumberExceptions("Numarul trebuie sa fie pozitiv!");
        }
        return Math.sqrt(operand);
    }
}
