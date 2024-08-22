package org.example.operations;

public class Subtraction extends BinaryOperation {

    public Subtraction(double operand1, double operand2) {
        super(operand1, operand2);
    }

    @Override
    public double calculate() {
        return operand1 - operand2;
    }
}
