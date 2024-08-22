package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class MergeFilesThreadTest {
    private Path splitFile1;
    private Path splitFile2;
    private List<Path> splitFiles;
    private AtomicLong validEntries;

    @BeforeEach
    void setUp() throws IOException {
        splitFile1 = Files.createTempFile("splitFile1", ".log");
        splitFile2 = Files.createTempFile("splitFile2", ".log");
        try (BufferedWriter writer = Files.newBufferedWriter(splitFile1)) {
            writer.write("line1\nline2\nsubject=math\nsubject=english\n");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(splitFile2)) {
            writer.write("line3\nline4\nline5\n");
        }
        splitFiles = new ArrayList<>();
        splitFiles.add(splitFile1);
        splitFiles.add(splitFile2);
        validEntries = new AtomicLong(0);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(splitFile1);
        Files.deleteIfExists(splitFile2);
    }

    @Test
    void testRun() {
        Thread thread = new Thread(new MergeFilesThread(splitFiles, validEntries));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(new AtomicLong(7).get(), validEntries.get());
    }

}