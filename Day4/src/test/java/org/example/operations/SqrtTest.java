package org.example.operations;

import org.example.exceptions.NegativeNumberExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {

    @Test
    void calculateSqrtTest() throws NegativeNumberExceptions {
        assertEquals(new Sqrt(4).calculate(),2);
        assertEquals(new Sqrt(0).calculate(),0);
        assertEquals(new Sqrt(144).calculate(),12);
        assertEquals(new Sqrt(10).calculate(), Math.sqrt(10));
        assertThrows(NegativeNumberExceptions.class, () -> new Sqrt(-1).calculate());
    }
}