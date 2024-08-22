package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileSplitter {
    private final Path filePath;
    private final int numberOfSplitFiles;
    private final Lock lock = new ReentrantLock();
    private final List<Path> splitFiles;

    public FileSplitter(String filePath, int numberOfSplitFiles) {
        this.filePath = Path.of(filePath);
        this.numberOfSplitFiles = numberOfSplitFiles;
        this.splitFiles = new ArrayList<>();
    }

    public void splitFile() throws IOException, InterruptedException, ExecutionException {
        BufferedReader reader = Files.newBufferedReader(filePath);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfSplitFiles);
        List<Future<?>> futures = submitSplittingTasks(executorService, reader);
        waitForTaskCompletion(futures);
        executorService.shutdown();
    }

    private static void waitForTaskCompletion(List<Future<?>> futures) throws InterruptedException, ExecutionException {
        for (Future<?> future : futures) {
            future.get();
        }
    }

    private List<Future<?>> submitSplittingTasks(ExecutorService executorService, BufferedReader reader) {
        List<Future<?>> futures = new ArrayList<>();
        for (int i = 0; i < numberOfSplitFiles; i++) {
            Path splitFilePath = filePath.resolveSibling(getSplitFileName(i));
            splitFiles.add(splitFilePath);
            futures.add(executorService.submit(new MyThread(reader, lock, splitFilePath)));
        }
        return futures;
    }

    private String getSplitFileName(int partition) {
        String fileName = filePath.getFileName().toString();
        String baseName = fileName.substring(0, fileName.lastIndexOf("."));
        return baseName + "." + partition + ".log";
    }

    public List<Path> getSplitFiles() {
        return splitFiles;
    }
}
