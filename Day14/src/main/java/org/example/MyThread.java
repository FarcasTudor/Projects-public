package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.locks.Lock;
import java.util.logging.Logger;

public class MyThread implements Runnable {
    private final Logger LOGGER = Logger.getLogger(MyThread.class.getName());
    private final BufferedReader reader;
    private final Lock lock;
    private final Path splitFilePath;

    public MyThread(BufferedReader reader, Lock lock, Path splitFilePath) {
        this.reader = reader;
        this.lock = lock;
        this.splitFilePath = splitFilePath;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = Files.newBufferedWriter(splitFilePath)) {
            String line;
            while (true) {
                line = readLine(reader);
                if (line == null) {
                    break;
                }
                writeLine(writer, line);
            }
        } catch (IOException e) {
            LOGGER.severe("Error processing file: " + splitFilePath);
        }
    }

    private String readLine(BufferedReader reader) throws IOException {
        lock.lock();
        try {
            return reader.readLine();
        } finally {
            lock.unlock();
        }
    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }
}
