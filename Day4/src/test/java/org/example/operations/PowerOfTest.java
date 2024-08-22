package org.example.operations;

import org.example.exceptions.PowerOfException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerOfTest {

    @Test
    void calculatePowerOfTest() throws PowerOfException {
        assertEquals(new PowerOf(2,3).calculate(),8);
        assertEquals(new PowerOf(2,0).calculate(),1);
        assertEquals(new PowerOf(2,-3).calculate(),0.125);
        assertEquals(new PowerOf(-2,-2).calculate(),0.25);
        assertEquals(new PowerOf(-2,3.9).calculate(),-8);
        assertThrows(PowerOfException.class, () -> new PowerOf(0, 0).calculate());
    }
}