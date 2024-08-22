package org.example.operations;

import org.example.exceptions.DivisionByZeroException;

public class Division extends BinaryOperation {

    public Division(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() throws DivisionByZeroException {
        if (operand2 == 0) {
            throw new DivisionByZeroException("Nu este posibila impartirea la 0!");
        }
        return operand1 / operand2;
    }
}
