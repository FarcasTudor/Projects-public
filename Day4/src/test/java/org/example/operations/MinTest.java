package org.example.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinTest {

    @Test
    void calculate() {
        assertEquals(2, new Min(2, 2).calculate());
        assertEquals(-2, new Min(-2, 2).calculate());
        assertEquals(-2, new Min(-2, -2).calculate());
    }
}