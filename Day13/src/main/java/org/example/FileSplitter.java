package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
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

    public void splitFile() throws IOException {
        BufferedReader reader = Files.newBufferedReader(filePath);
        Thread[] threads = new Thread[numberOfSplitFiles];
        for (int i = 0; i < numberOfSplitFiles; i++) {
            Path splitFilePath = filePath.resolveSibling(getSplitFileName(i));
            splitFiles.add(splitFilePath);
            threads[i] = new Thread(new MyThread(reader, lock, splitFilePath));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        reader.close();
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
