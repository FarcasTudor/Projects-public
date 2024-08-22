package org.example;

import org.example.interfaces.InMemoryRepository;
import org.example.model.Order;
import org.example.repositories.TreeSetBasedRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 2)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Threads(1)
public class TreeSetBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private final InMemoryRepository<Order> treeSetBasedRepository = new TreeSetBasedRepository<>();

        @Setup(Level.Trial)
        public void setup() {
            for (int i = 0; i < 10000; i++) {
                treeSetBasedRepository.add(new Order(i, i, i));
            }
        }
    }

    @Benchmark
    public void testAddTreeSet(MyState state) {
        state.treeSetBasedRepository.add(new Order(10001, 10001, 10001));
    }

    @Benchmark
    public void testContainsTreeSet(MyState state) {
        state.treeSetBasedRepository.contains(new Order(1, 1, 1));
    }

    @Benchmark
    public void testRemoveTreeSet(MyState state) {
        state.treeSetBasedRepository.remove(new Order(1, 1, 1));
    }
}
