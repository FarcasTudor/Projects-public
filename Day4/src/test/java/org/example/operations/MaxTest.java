package org.example.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxTest {

    @Test
    void calculateMaxTest() {
        assertEquals(2, new Max(2, 2).calculate());
        assertEquals(2, new Max(-2, 2).calculate());
        assertEquals(-2, new Max(-2, -2).calculate());
    }
}