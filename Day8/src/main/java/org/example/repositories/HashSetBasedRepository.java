package org.example.repositories;

import org.example.interfaces.InMemoryRepository;

import java.util.HashSet;
import java.util.Set;

public class HashSetBasedRepository <T> implements InMemoryRepository<T> {
    private final Set<T> set;

    public HashSetBasedRepository() {
        this.set = new HashSet<>();
    }

    @Override
    public void add(T entity) {
        set.add(entity);
    }

    @Override
    public boolean contains(T entity) {
        return set.contains(entity);
    }

    @Override
    public void remove(T entity) {
        set.remove(entity);
    }
}
