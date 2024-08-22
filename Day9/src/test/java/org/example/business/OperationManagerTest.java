package org.example.business;

import org.example.exceptions.DivisionByZeroException;
import org.example.exceptions.NegativeNumberExceptions;
import org.example.exceptions.PowerOfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationManagerTest {

    @Test
    void testAddition() {
        assertEquals(5, OperationManager.getBinaryOperationResult(2, 3, "+"));
        assertEquals(1, OperationManager.getBinaryOperationResult(3, -2, "+"));
        assertEquals(-6, OperationManager.getBinaryOperationResult(-3, -3, "+"));
    }

    @Test
    void testSubtraction() {
        assertEquals(2, OperationManager.getBinaryOperationResult(5, 3, "-"));
        assertEquals(5, OperationManager.getBinaryOperationResult(3, -2, "-"));
        assertEquals(0, OperationManager.getBinaryOperationResult(-3, -3, "-"));
    }

    @Test
    void testMultiply() {
        assertEquals(6, OperationManager.getBinaryOperationResult(2, 3, "*"));
        assertEquals(-6, OperationManager.getBinaryOperationResult(2, -3, "*"));
        assertEquals(6, OperationManager.getBinaryOperationResult(-2, -3, "*"));
    }

    @Test
    void testDivision() {
        assertEquals(2, OperationManager.getBinaryOperationResult(6, 3, "/"));
        assertThrows(DivisionByZeroException.class, () -> OperationManager.getBinaryOperationResult(6, 0, "/"));
    }

    @Test
    void testMin() {
        assertEquals(2, OperationManager.getBinaryOperationResult(2, 3, "min"));
        assertEquals(-100, OperationManager.getBinaryOperationResult(-100, 1, "min"));
    }

    @Test
    void testMax() {
        assertEquals(3, OperationManager.getBinaryOperationResult(2, 3, "max"));
        assertEquals(-1, OperationManager.getBinaryOperationResult(-1, -10, "max"));
    }

    @Test
    void testPower() {
        assertEquals(8, OperationManager.getBinaryOperationResult(2, 3, "^"));
        assertThrows(PowerOfException.class, () -> OperationManager.getBinaryOperationResult(0, 0, "^"));
    }

    @Test
    void testSqrt() {
        assertEquals(2, OperationManager.getUnaryOperationResult(4, "sqrt"));
        assertThrows(NegativeNumberExceptions.class, () -> OperationManager.getUnaryOperationResult(-4, "sqrt"));
    }

    @Test
    void testRound() {
        assertEquals(3, OperationManager.getUnaryOperationResult(3.4, "round"));
    }

}