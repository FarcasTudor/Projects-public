package org.example.repositories;

import org.example.interfaces.InMemoryRepository;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetBasedRepository <T> implements InMemoryRepository<T> {
    private final Set<T> set;

    public TreeSetBasedRepository() {
        this.set = new TreeSet<>();
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
