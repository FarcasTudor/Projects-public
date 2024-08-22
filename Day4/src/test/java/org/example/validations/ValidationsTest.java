package org.example.validations;

import org.example.enums.OperationSymbol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsTest {

    @Test
    void validateOperation() {
        assertTrue(OperationSymbol.isValid("+"));
        assertTrue(OperationSymbol.isValid("-"));
        assertTrue(OperationSymbol.isValid("*"));
        assertTrue(OperationSymbol.isValid("/"));
        assertFalse(OperationSymbol.isValid("a"));
        assertFalse(OperationSymbol.isValid("1"));
        assertFalse(OperationSymbol.isValid(" "));
    }
}