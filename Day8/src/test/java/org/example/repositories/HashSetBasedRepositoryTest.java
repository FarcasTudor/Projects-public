package org.example.repositories;

import org.example.interfaces.InMemoryRepository;
import org.example.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashSetBasedRepositoryTest {

    private final InMemoryRepository<Order> hashSetBasedRepository = new HashSetBasedRepository<>();

    @Test
    void add() {
        Order order = new Order(1, 10, 100);
        hashSetBasedRepository.add(order);
        assertTrue(hashSetBasedRepository.contains(order));
    }

    @Test
    void contains() {
        Order order = new Order(1, 1, 1);
        Order order2 = new Order(2, 2, 2);
        hashSetBasedRepository.add(order);
        assertTrue(hashSetBasedRepository.contains(order));
        assertFalse(hashSetBasedRepository.contains(order2));
    }

    @Test
    void remove() {
        Order order = new Order(1, 1, 1);
        hashSetBasedRepository.add(order);
        hashSetBasedRepository.remove(order);
        assertFalse(hashSetBasedRepository.contains(order));
    }

}