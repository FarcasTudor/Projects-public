package org.example;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StreamMethods {
    public static int maxInt(int n) {
        int max = 0;
        for (int i = 0; i <= n; i++) {
            int aux = i + 1;
            max = max < aux ? aux : max;
        }
        return max;
    }

    public static int maxInteger(int n) {
        int max = 0;
        for (int i = 0; i <= n; i++) {
            Integer aux = i + 1;
            max = max < aux ? aux : max;
        }
        return max;
    }

    public static Optional<Integer> maxListOfStream(List<Integer> list) {
        return list.stream()
                .map(i -> i + 1)
                .max(Integer::compareTo);
    }

    public static OptionalInt maxIntStreamRangeClosed(int end) {
        return IntStream.rangeClosed(1, end)
                .map(i -> i + 1)
                .max();
    }

    public static Optional<Integer> maxListOfStreamParallel(List<Integer> list) {
        return list.stream()
                .parallel()
                .map(i -> i + 1)
                .max(Integer::compareTo);
    }

    public static OptionalInt maxIntStreamRangeClosedParallel(int end) {
        return IntStream.rangeClosed(1, end)
                .parallel()
                .map(i -> i + 1)
                .max();
    }

    public static List<Integer> generateListOfElements(int size) {
        return IntStream.rangeClosed(1, size)
                .boxed()
                .toList();
    }
}
