package org.example.operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    void calculateRoundTest() {
        assertEquals(new Round(2.3).calculate(),2);
        assertEquals(new Round(2.5).calculate(),3);
        assertEquals(new Round(2.7).calculate(),3);
        assertEquals(new Round(-2.3).calculate(),-2);
        assertEquals(new Round(-2.5).calculate(),-2);
        assertEquals(new Round(-2.7).calculate(),-3);
    }
}