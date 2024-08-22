package org.example.operations;

public class Round extends UnaryOperation {

    public Round(double operand) {
        super(operand);
    }

    @Override
    public double calculate() {
        return Math.round(operand);
    }
}
