package org.example;

import org.openjdk.jmh.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode({Mode.AverageTime})
@Fork(value = 2)
@Warmup(iterations = 5, time = 20, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5)
@Threads(1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StreamMethodsBenchmark {


    @State(Scope.Thread)
    public static class MyState {
        private List<Integer> list;

        @Param({"5", "5000000"})
        public int n;

        @Setup(Level.Trial)
        public void setup() {
            list = StreamMethods.generateListOfElements(n);
        }
    }

    @Benchmark
    public void testMaxInt(MyState state) {
        StreamMethods.maxInt(state.n);
    }

    @Benchmark
    public void testMaxInteger(MyState state) {
        StreamMethods.maxInteger(state.n);
    }

    @Benchmark
    public void testMaxListOfStream(MyState state) { // best one
        StreamMethods.maxListOfStream(state.list);
    }

    @Benchmark
    public void testMaxIntStreamRangeClosed(MyState state) {
        StreamMethods.maxIntStreamRangeClosed(state.n);
    }

    @Benchmark
    public void testMaxListOfStreamParallel(MyState state) { // second best
        StreamMethods.maxListOfStreamParallel(state.list);
    }

    @Benchmark
    public void testMaxIntStreamRangeClosedParallel(MyState state) {
        StreamMethods.maxIntStreamRangeClosedParallel(state.n);
    }
}
