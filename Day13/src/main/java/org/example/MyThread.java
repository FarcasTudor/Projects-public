package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.locks.Lock;

public class MyThread implements Runnable {
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
                lock.lock();
                try {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
