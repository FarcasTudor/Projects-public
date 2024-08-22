package org.example.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationTest {

    @Test
    void calculateMultiplicationTest() {
        assertEquals(4, new Multiplication(2, 2).calculate());
        assertEquals(-4, new Multiplication(-2, 2).calculate());
        assertEquals(4, new Multiplication(-2, -2).calculate());
    }
}