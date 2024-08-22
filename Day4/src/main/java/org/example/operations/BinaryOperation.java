package org.example.operations;

import org.example.interfaces.Operation;

public abstract class BinaryOperation implements Operation {
    protected final double operand1;
    protected final double operand2;

    public BinaryOperation(double operand1, double operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }
}
