package org.example.operations;

import org.example.exceptions.PowerOfException;

public class PowerOf extends BinaryOperation {

    public PowerOf(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() throws PowerOfException {
        if (operand1 == 0 && operand2 == 0) {
            throw new PowerOfException("0^0 nu exista!");
        }
        if (operand1 < 0) {
            return Math.pow(operand1, (int) operand2);
        }
        return Math.pow(operand1, operand2);
    }
}
