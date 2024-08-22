package org.example.repositories;

import org.example.interfaces.InMemoryRepository;
import org.example.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListBasedRepositoryTest {
    private final InMemoryRepository<Order> arrayListBasedRepository = new ArrayListBasedRepository<>();

    @Test
    void add() {
        Order order = new Order(1, 10, 100);
        arrayListBasedRepository.add(order);
        assertTrue(arrayListBasedRepository.contains(order));
    }

    @Test
    void contains() {
        Order order = new Order(1, 1, 1);
        Order order2 = new Order(2, 2, 2);
        arrayListBasedRepository.add(order);
        assertTrue(arrayListBasedRepository.contains(order));
        assertFalse(arrayListBasedRepository.contains(order2));
    }

    @Test
    void remove() {
        Order order = new Order(1, 1, 1);
        arrayListBasedRepository.add(order);
        arrayListBasedRepository.remove(order);
        assertFalse(arrayListBasedRepository.contains(order));
    }
}