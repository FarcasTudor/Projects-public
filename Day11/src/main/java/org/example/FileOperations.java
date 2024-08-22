package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Utils.getTopEntries;

public class FileOperations {

    //private static final String filePath = "Day11/src/main/java/org/example/trilava-2021-07-05-POST.log";
    private static final String filePath = "Day11/src/main/java/org/example/smallFile.txt";

    public static void findLinesWithText(String text) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.contains(text)) {
                System.out.println(line);
            }
            line = bufferedReader.readLine();
        }
        System.out.println("End of file");
    }

    public static int countNumberOfOccurences(String text, String filePath) throws IOException {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        int count = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String word = matcher.group();
                    if (word.equals(text)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static Map<String, Integer> findMostFrequentlyUsedSubject(int limit, String filePath) throws IOException {
        Pattern pattern = Pattern.compile("subject=([^,]*)");
        Map<String, Integer> map = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String subject = matcher.group(1);
                    map.put(subject, map.getOrDefault(subject, 0) + 1);
                }
            }
        }

        return getTopEntries(map, limit);
    }

    public static Map<String, Integer> findMostFrequentlyUsedWords(int limit, String filePath) throws IOException {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Map<String, Integer> map = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String word = matcher.group();
                    map.put(word, map.getOrDefault(word, 0) + 1);
                }
            }
        }
        return getTopEntries(map, limit);
    }

    public static void replacementPattern(String oldText, String newText, String filePath) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = getFile(oldText, newText, inputFile);

        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                throw new IOException("Nu am putut da rename la fisier");
            }
        } else {
            throw new IOException("Nu s-a realizar stergerea fisierului");
        }

        System.out.println("Replacement a functionat");
    }

    private static File getFile(String oldText, String newText, File inputFile) throws IOException {
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(oldText)) {
                    line = line.replace(oldText, newText);
                }
                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
        return tempFile;
    }

    public static void removePattern(String text) throws IOException {
        File inputFile = new File(filePath);
        File tempFile = getFile(text, "", inputFile);

        if (inputFile.delete()) {
            if (!tempFile.renameTo(inputFile)) {
                throw new IOException("Nu am putut da rename la fisier");
            }
        } else {
            throw new IOException("Nu s-a realizat stergerea fisierului");
        }

        System.out.println("Remove a functionat");
    }


}