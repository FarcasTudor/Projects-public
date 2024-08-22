package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessingWorker implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(FileProcessingWorker.class.getName());
    private final List<Path> splitFiles;
    private final AtomicLong counterOfLines;

    public FileProcessingWorker(List<Path> splitFiles, AtomicLong counterOfLines) {
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
                LOGGER.log(Level.SEVERE, "Error processing file: " + splitFile, e);
            }
        }
    }
}
