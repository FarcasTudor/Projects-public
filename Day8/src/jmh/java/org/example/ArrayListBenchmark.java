package org.example;


import org.example.interfaces.InMemoryRepository;
import org.example.model.Order;
import org.example.repositories.ArrayListBasedRepository;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@Fork(value = 2)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Threads(1)
public class ArrayListBenchmark {


    @State(Scope.Thread)
    public static class MyState {

        private final InMemoryRepository<Order> arrayListBasedRepository = new ArrayListBasedRepository<>();

        @Setup(Level.Trial)
        public void setup() {
            for (int i = 0; i < 100; i++) {
                arrayListBasedRepository.add(new Order(i, i, i));
            }
        }
    }

    @Benchmark
    public void testAddArrayList(MyState state) {
        state.arrayListBasedRepository.add(new Order(101, 10001, 10001));

    }

    @Benchmark
    public void testContainsArrayList(MyState state) {
        state.arrayListBasedRepository.contains(new Order(10001, 10001, 10001));
    }

    @Benchmark
    public void testRemoveArrayList(MyState state) {
        state.arrayListBasedRepository.remove(new Order(10001, 10001, 10001));
    }


}
