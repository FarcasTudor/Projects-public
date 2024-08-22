package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class MergeFilesThread implements Runnable {
    private final List<Path> splitFiles;
    private final AtomicLong counterOfLines;

    public MergeFilesThread(List<Path> splitFiles, AtomicLong counterOfLines) {
        this.splitFiles = splitFiles;
        this.counterOfLines = counterOfLines;
    }

    @Override
    public void run() {
        for (Path splitFile : splitFiles) {
            try (BufferedReader reader = Files.newBufferedReader(splitFile)) {
                while (reader.readLine() != null) {
                    counterOfLines.getAndIncrement();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
