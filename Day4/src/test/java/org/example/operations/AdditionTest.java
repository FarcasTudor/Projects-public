package org.example.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {
    @Test
    public void additionTest() {
        assertEquals(4, new Addition(2, 2).calculate());
        assertEquals(0, new Addition(-2, 2).calculate());
        assertEquals(-4, new Addition(-2, -2).calculate());
    }
}