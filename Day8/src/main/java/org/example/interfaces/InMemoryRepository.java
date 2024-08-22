package org.example.interfaces;

public interface InMemoryRepository <T> {
    void add(T entity);
    boolean contains(T entity);
    void remove(T entity);
}
