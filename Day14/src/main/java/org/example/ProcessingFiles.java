package org.example;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ProcessingFiles {
    private final AtomicLong validEntries;
    private final int numThreads;
    private final List<Path> splitFiles;

    public ProcessingFiles(List<Path> splitFiles, int numThreads) {
        this.splitFiles = splitFiles;
        this.numThreads = numThreads;
        this.validEntries = new AtomicLong(0);
    }

    public void processFiles() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        List<Future<?>> futures = submitProcessingTasks(executorService);
        waitForTaskCompletion(futures);
        executorService.shutdown();
        System.out.println("Number of valid entries phase 2: " + validEntries.get());
    }

    private void waitForTaskCompletion(List<Future<?>> futures) throws ExecutionException, InterruptedException {
        for ( Future<?> future : futures) {
            future.get();
        }
    }

    private List<Future<?>> submitProcessingTasks(ExecutorService executorService) {
        List<Future<?>> futures = new java.util.ArrayList<>();
        int filerPerThread = (int) Math.ceil((double)splitFiles.size() / numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * filerPerThread;
            int end = Math.min(start + filerPerThread, splitFiles.size());
            if(start < end) {
                futures.add(executorService.submit(new FileProcessingWorker(splitFiles.subList(start, end), validEntries)));
            }
        }
        return futures;
    }

    public long getValidEntries() {
        return validEntries.get();
    }
}
