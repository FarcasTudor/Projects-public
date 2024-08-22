package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;

class MyThreadTest {
    private Path tempFile;
    private Path splitFile;
    private BufferedReader reader;
    private ReentrantLock lock;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("testFile", ".log");
        Files.write(tempFile, "line1\nline2\nline3\nline4\nline5\n".getBytes());
        reader = Files.newBufferedReader(tempFile);
        lock = new ReentrantLock();
        splitFile = Files.createTempFile("splitFile", ".log");
    }

    @AfterEach
    void tearDown() throws IOException {
        reader.close();
        Files.deleteIfExists(tempFile);
        Files.deleteIfExists(splitFile);
    }

    @Test
    void testRun() throws IOException {
        Thread thread = new Thread(new MyThread(reader, lock, splitFile));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> lines = Files.readAllLines(splitFile);
        assertEquals(5, lines.size());
        assertEquals("line1", lines.get(0));
        assertEquals("line5", lines.get(4));
    }
}