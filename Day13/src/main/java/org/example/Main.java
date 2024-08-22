package org.example;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws IOException {
        final String filePath = "Day13/src/main/java/org/example/trilava-2021-07-05-POST.log";
        final int n = 4;
        int numOfThreads = 8;
        try {
            FileSplitter fileSplitter = new FileSplitter(filePath, n);
            fileSplitter.splitFile();

            AtomicLong counterOfLines = new AtomicLong(0);
            Thread phase1 = new Thread(new MergeFilesThread(fileSplitter.getSplitFiles(), counterOfLines));

            phase1.start();
            phase1.join();

            System.out.println("Number of valid entries phase 1: " + counterOfLines);

            Phase2 phase2 = new Phase2(fileSplitter.getSplitFiles(), numOfThreads);
            phase2.processFiles();

        } catch (/*IOException |*/ InterruptedException e) {
            e.printStackTrace();
        }

    }
}