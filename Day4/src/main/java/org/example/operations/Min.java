package org.example.operations;

public class Min extends BinaryOperation {

    public Min(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return Math.min(operand1, operand2);
    }
}
