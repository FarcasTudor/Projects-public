package org.example.validations;

import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.NegativeNumberException;
import org.example.exceptions.PowerOfException;

public class Validations {
    public static void validateDivision(double b) throws DivisionByZeroException {
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero is not allowed!");
        }
    }

    public static void validateSqrt(double a) throws NegativeNumberException {
        if (a < 0) {
            throw new NegativeNumberException("Square root of a negative number is not allowed!");
        }
    }

    public static void validatePower(double a, double b) throws PowerOfException {
        if (a == 0 && b == 0) {
            throw new PowerOfException("0^0 is not defined!");
        }
    }
}
