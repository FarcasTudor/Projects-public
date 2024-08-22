package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ProcessingFilesTest {

    @TempDir
    Path tempDir;

    private ProcessingFiles processingFilesMoreThreads;
    private ProcessingFiles processingFilesLessThreads;

    @BeforeEach
    public void setUp() throws Exception {
        Path file1 = Files.createFile(tempDir.resolve("splitFile1.log"));
        Path file2 = Files.createFile(tempDir.resolve("splitFile2.log"));
        Path file3 = Files.createFile(tempDir.resolve("splitFile3.log"));

        try (BufferedWriter writer = Files.newBufferedWriter(file1)) {
            writer.write("line1\nline2\nsubject=math\nsubject=english\n");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file2)) {
            writer.write("line3\nline4\nline5\n");
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file3)) {
            writer.write("line6\nline7\nline8\n");
        }
        List<Path> splitFiles = Arrays.asList(file1, file2, file3);
        processingFilesMoreThreads = new ProcessingFiles(splitFiles, 6);
        processingFilesLessThreads = new ProcessingFiles(splitFiles, 2);
    }

    @Test
    void processFiles() throws ExecutionException, InterruptedException {
        processingFilesMoreThreads.processFiles();
        assertEquals(10L, processingFilesMoreThreads.getValidEntries());
        processingFilesLessThreads.processFiles();
        assertEquals(10L, processingFilesLessThreads.getValidEntries());
    }
}