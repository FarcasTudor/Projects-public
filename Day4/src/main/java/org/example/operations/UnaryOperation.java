package org.example.operations;

import org.example.interfaces.Operation;

public abstract class UnaryOperation implements Operation {
    protected final double operand;

    public UnaryOperation(double operand) {
        this.operand = operand;
    }
}
