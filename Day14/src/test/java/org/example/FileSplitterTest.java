package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileSplitterTest {
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = Files.createTempFile("testFileSplitter", ".log");
        Files.write(tempFile, "line1\nline2\nline3\nline4\nline5\n".getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testSplitFile() throws IOException, InterruptedException, ExecutionException {
        FileSplitter splitter = new FileSplitter(tempFile.toString(), 2);
        splitter.splitFile();
        List<Path> splitFiles = splitter.getSplitFiles();

        assertEquals(2, splitFiles.size());
        assertTrue(Files.exists(splitFiles.get(0)));
        assertTrue(Files.exists(splitFiles.get(1)));
    }
}