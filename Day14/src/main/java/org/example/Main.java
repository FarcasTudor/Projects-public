package org.example;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        final String filePath = "Day14/src/main/java/org/example/trilava-2021-07-05-POST.log";
        final int n = 4;
        int numOfThreads = 8;
        try {
            FileSplitter fileSplitter = new FileSplitter(filePath, n);
            fileSplitter.splitFile();

            AtomicLong counterOfLines = new AtomicLong(0);
            Thread phase1 = new Thread(new FileProcessingWorker(fileSplitter.getSplitFiles(), counterOfLines));

            phase1.start();
            phase1.join();

            System.out.println("Number of valid entries phase 1: " + counterOfLines);

            ProcessingFiles phase2 = new ProcessingFiles(fileSplitter.getSplitFiles(), numOfThreads);
            phase2.processFiles();

        } catch (IOException | InterruptedException | ExecutionException e) {
            LOGGER.severe("Error processing file: " + filePath);
        }
    }
}