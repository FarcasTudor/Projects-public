package org.example;

import org.example.interfaces.InMemoryRepository;
import org.example.model.Order;
import org.example.repositories.HashSetBasedRepository;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 2)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Threads(1)
public class HashSetBenchmark {

    @State(Scope.Thread)
    public static class MyState {
        private final InMemoryRepository<Order> hashSetBasedRepository = new HashSetBasedRepository<>();

        @Setup(Level.Trial)
        public void setup() {
            for (int i = 0; i < 10000; i++) {
                hashSetBasedRepository.add(new Order(i, i, i));
            }
        }
    }

    @Benchmark
    public void testAddHashSet(MyState state) {
        state.hashSetBasedRepository.add(new Order(100001, 1, 1));
    }

    @Benchmark
    public void testContainsHashSet(MyState state) {
        state.hashSetBasedRepository.contains(new Order(1, 1, 1));
    }

    @Benchmark
    public void testRemoveHashSet(MyState state) {
        state.hashSetBasedRepository.remove(new Order(1, 1, 1));
    }

}
