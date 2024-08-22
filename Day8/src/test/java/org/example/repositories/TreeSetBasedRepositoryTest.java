package org.example.repositories;

import org.example.interfaces.InMemoryRepository;
import org.example.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetBasedRepositoryTest {
    private final InMemoryRepository<Order> treeSetBasedRepository = new TreeSetBasedRepository<>();

    @Test
    void add() {
        Order order = new Order(1, 10, 100);
        treeSetBasedRepository.add(order);
        assertTrue(treeSetBasedRepository.contains(order));
    }

    @Test
    void contains() {
        Order order = new Order(1, 1, 1);
        Order order2 = new Order(2, 2, 2);
        treeSetBasedRepository.add(order);
        assertTrue(treeSetBasedRepository.contains(order));
        assertFalse(treeSetBasedRepository.contains(order2));
    }

    @Test
    void remove() {
        Order order = new Order(1, 1, 1);
        treeSetBasedRepository.add(order);
        treeSetBasedRepository.remove(order);
        assertFalse(treeSetBasedRepository.contains(order));
    }
}