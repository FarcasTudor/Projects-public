package org.example.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtractionTest {

    @Test
    void calculateSubtractionTest() {
        assertEquals(new Subtraction(1,11).calculate(),-10);
        assertEquals(new Subtraction(1,-1).calculate(),2);
        assertEquals(new Subtraction(-1,-1).calculate(),0);
    }
}