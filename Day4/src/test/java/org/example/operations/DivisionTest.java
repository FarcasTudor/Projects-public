package org.example.operations;

import org.example.exceptions.DivisionByZeroException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionTest {

    @Test
    void calculateDivisionTest() throws DivisionByZeroException {
        assertEquals(1, new Division(2, 2).calculate());
        assertEquals(-1, new Division(-2, 2).calculate());
        assertEquals(1, new Division(-2, -2).calculate());
        assertThrows(DivisionByZeroException.class, () -> new Division(2, 0).calculate());
    }
}