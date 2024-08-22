package org.example.operations;

public class Max extends BinaryOperation {

    public Max(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return Math.max(operand1, operand2);
    }
}
