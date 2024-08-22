package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SumTwoIntegersTest {

    SumTwoIntegers sumTwoIntegers;

    @BeforeEach
    public void setUp() {
        sumTwoIntegers = new SumTwoIntegers();
    }

    @Test
    public void addTestNegativeNumbers() {
        assertThrows(ArithmeticException.class, () -> sumTwoIntegers.add(-1, -1));
    }

    @Test
    public void addTestFirstNumberNegative() {
        assertThrows(ArithmeticException.class, () -> sumTwoIntegers.add(-1, 1));
    }

    @Test
    public void addTestAboveMaxValue() {
        assertThrows(ArithmeticException.class, () -> sumTwoIntegers.add(Integer.MAX_VALUE / 2 + 1, Integer.MAX_VALUE / 2 + 2));
    }

    @Test
    public void addTestAboveMaxValueFirst() {
        assertThrows(ArithmeticException.class, () -> sumTwoIntegers.add(Integer.MAX_VALUE / 2 + 1, 2));
    }

    @Test
    public void addTestAboveMaxValueSecond() {
        assertThrows(ArithmeticException.class, () -> sumTwoIntegers.add(1, Integer.MAX_VALUE / 2 + 2));
    }

    @Test
    public void addTestWorking() {
        assertEquals(sumTwoIntegers.add(1, 2), 3);
    }
}
