package org.example.business;

import org.example.exceptions.CalculatorExceptions;
import org.example.exceptions.NegativeNumberExceptions;
import org.example.exceptions.OperationNotFoundException;
import org.example.exceptions.PowerOfException;
import org.example.operations.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationManagerTest {

    @Test
    void getBinaryOperation() throws OperationNotFoundException {
        assertEquals(OperationManager.getBinaryOperation(1,2,"+").getClass(), Addition.class);
        assertEquals(OperationManager.getBinaryOperation(1,2,"-").getClass(), Subtraction.class);
        assertEquals(OperationManager.getBinaryOperation(1,2,"*").getClass(), Multiplication.class);
        assertEquals(OperationManager.getBinaryOperation(1,2,"/").getClass(), Division.class);
        assertEquals(OperationManager.getBinaryOperation(1,2,"max").getClass(), Max.class);
        assertEquals(OperationManager.getBinaryOperation(1,2,"min").getClass(), Min.class);
        assertEquals(OperationManager.getBinaryOperation(1,2,"^").getClass(), PowerOf.class);
        assertThrows(OperationNotFoundException.class, () -> OperationManager.getBinaryOperationResult(1,2,"invalid"));
        assertThrows(PowerOfException.class, () -> OperationManager.getBinaryOperationResult(0,0,"^"));
    }

    @Test
    void getUnaryOperation() throws OperationNotFoundException {
        assertEquals(OperationManager.getUnaryOperation(1,"sqrt").getClass(), Sqrt.class);
        assertEquals(OperationManager.getUnaryOperation(1,"round").getClass(), Round.class);
        assertThrows(OperationNotFoundException.class, () -> OperationManager.getUnaryOperation(1,"invalid"));
        assertThrows(NegativeNumberExceptions.class, () -> OperationManager.getUnaryOperationResult(-1,"sqrt"));
    }

    @Test
    void getBinaryOperationResult() throws CalculatorExceptions {
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"+"),3);
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"-"),-1);
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"*"),2);
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"/"),0.5);
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"max"),2);
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"min"),1);
        assertEquals(OperationManager.getBinaryOperationResult(1,2,"^"),1);
    }

    @Test
    void getUnaryOperationResult() throws CalculatorExceptions {
        assertEquals(OperationManager.getUnaryOperationResult(9,"sqrt"),3);
        assertEquals(OperationManager.getUnaryOperationResult(1.51,"round"),2);
    }
}