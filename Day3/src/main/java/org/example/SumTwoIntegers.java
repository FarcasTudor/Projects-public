package org.example;

import static java.lang.Integer.MAX_VALUE;

public class SumTwoIntegers {
    public int add(int x, int y) throws ArithmeticException {
        if (x < 0 || y < 0) {
            throw new ArithmeticException("Parameters must be positive!");
        }
        if (x >= MAX_VALUE / 2 || y >= MAX_VALUE / 2) {
            throw new ArithmeticException("Parameters must be less than MAX_VALUE / 2 !!!");
        }

        return x + y;
    }
}
