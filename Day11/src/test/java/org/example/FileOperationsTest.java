package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileOperationsTest {
    private static final String filePathOccurencies = "C:\\Users\\intern-2\\Tudor\\LSEG_PROJECTS\\Projects\\Day11\\src\\test\\java\\org\\example\\testFiles\\noOfOccurrences";
    private static final String filePathSubjects = "C:\\Users\\intern-2\\Tudor\\LSEG_PROJECTS\\Projects\\Day11\\src\\test\\java\\org\\example\\testFiles\\mostSubjects";
    private static final String filePathReplacement = "C:\\Users\\intern-2\\Tudor\\LSEG_PROJECTS\\Projects\\Day11\\src\\test\\java\\org\\example\\testFiles\\replacePattern";

    @Test
    void countNumberOfOccurences() throws IOException {
        assertEquals(2, FileOperations.countNumberOfOccurences("casa", filePathOccurencies));
        assertEquals(0, FileOperations.countNumberOfOccurences("NUEXISTA", filePathOccurencies));
    }

    @Test
    void findMostFrequentlyUsedSubject() throws IOException {
        Map<String, Integer> map = FileOperations.findMostFrequentlyUsedSubject(2, filePathSubjects);
        Map.Entry<String, Integer> entry = map.entrySet().iterator().next();

        assertEquals(2, map.size());
        assertEquals("TUDOR.FARCAS", entry.getKey());
        assertEquals(3, entry.getValue());

        map.remove(entry.getKey());
        entry = map.entrySet().iterator().next();

        assertEquals("TORA", entry.getKey());
        assertEquals(2, entry.getValue());
    }

    @Test
    void findMostFrequentlyUsedWords() throws IOException {
        Map<String, Integer> map = FileOperations.findMostFrequentlyUsedWords(2, filePathSubjects);
        Map.Entry<String, Integer> entry = map.entrySet().iterator().next();

        assertEquals(2, map.size());
        assertEquals("subject", entry.getKey());
        assertEquals(8, entry.getValue());
    }

    @Test
    void replacementPattern() throws IOException {
        assertEquals(3, FileOperations.countNumberOfOccurences("casa", filePathReplacement));
        FileOperations.replacementPattern("casa", "Masa", filePathReplacement);
        assertEquals(0, FileOperations.countNumberOfOccurences("casa", filePathReplacement));
        FileOperations.replacementPattern("Masa", "casa", filePathReplacement);
        assertEquals(3, FileOperations.countNumberOfOccurences("casa", filePathReplacement));
    }
}