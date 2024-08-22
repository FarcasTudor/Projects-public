package org.example;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Phase2 {
    private final AtomicLong validEntries;
    private final int numThreads;
    private final List<Path> splitFiles;

    public Phase2(List<Path> splitFiles, int numThreads) {
        this.splitFiles = splitFiles;
        this.numThreads = numThreads;
        this.validEntries = new AtomicLong(0);
    }

    public void processFiles() throws InterruptedException {
        Thread[] threads = new Thread[numThreads];
        int filerPerThread = (int) Math.ceil((double)splitFiles.size() / numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * filerPerThread;
            int end = Math.min(start + filerPerThread, splitFiles.size());
            if(start < end) {
                threads[i] = new Thread(new MergeFilesThread(splitFiles.subList(start, end), validEntries));
                threads[i].start();
            }
        }

        for (Thread thread : threads) {
            if(thread != null) {
                thread.join();
            }
        }

        System.out.println("Number of valid entries phase 2: " + validEntries.get());
    }
}
