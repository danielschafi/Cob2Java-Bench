import java.io.*;
import java.util.*;

public class Deranged {
    private static final int MAX_RECORDS = 24000;
    private static final int MAX_WORD_LENGTH = 22;
    private static final int MAX_ANAGRAMS = 6;

    private static boolean eof = false;
    private static boolean found = false;
    private static boolean exit = false;
    private static int totalRecords = 0;
    private static int selectedRecords = 0;
    private static int recordNumber = 0;

    private static char[][] wordTable = new char[MAX_RECORDS][MAX_WORD_LENGTH];
    private static int[] wordLengths = new int[MAX_RECORDS];
    private static int[] anagramCounts = new int[MAX_RECORDS];
    private static char[][][] anagrams = new char[MAX_RECORDS][MAX_ANAGRAMS][MAX_WORD_LENGTH];

    private static char[] wordIn = new char[MAX_WORD_LENGTH];
    private static char[] wordKey = new char[MAX_WORD_LENGTH];
    private static char[] wordTemp = new char[MAX_WORD_LENGTH + 1];
    private static char[] wordTemp1 = new char[MAX_WORD_LENGTH];
    private static char[] wordTemp2 = new char[MAX_WORD_LENGTH];
    private static char[] letter = new char[MAX_WORD_LENGTH + 1];
    private static char letterHld;

    private static int i, j, k, l, beg, max;

    public static void main(String[] args) {
        initialize();
        while (!eof) {
            processRecord();
        }
        sortWordTable();
        findDeranged();
        terminate();
    }

    private static void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Anagrams.txt"))) {
            readRecord(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readRecord(BufferedReader reader) {
        try {
            String line = reader.readLine();
            if (line == null) {
                eof = true;
            } else {
                totalRecords++;
                line.getChars(0, Math.min(line.length(), MAX_WORD_LENGTH), wordIn, 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord() {
        if (isAlphabetic(wordIn)) {
            selectedRecords++;
            System.arraycopy(wordIn, 0, wordTemp, 0, MAX_WORD_LENGTH);
            sortWord();
            System.arraycopy(wordTemp, 0, wordKey, 0, MAX_WORD_LENGTH);
            addToTable();
        }
        readRecord(null);
    }

    private static boolean isAlphabetic(char[] word) {
        for (char c : word) {
            if (c != 0 && !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private static void sortWord() {
        max = 0;
        while (wordTemp[max] != 0) {
            max++;
        }
        for (i = 1; i < max; i++) {
            for (j = i + 1; j < max; j++) {
                if (letter[j] < letter[i]) {
                    letterHld = letter[i];
                    letter[i] = letter[j];
                    letter[j] = letterHld;
                }
            }
        }
    }

    private static void addToTable() {
        i = 1;
        while (i <= recordNumber && !wordKey.equals(wordTable[i - 1])) {
            i++;
        }
        if (i > recordNumber) {
            addRecord();
        } else {
            updateRecord(i - 1);
        }
    }

    private static void addRecord() {
        recordNumber++;
        System.arraycopy(wordKey, 0, wordTable[recordNumber - 1], 0, MAX_WORD_LENGTH);
        wordLengths[recordNumber - 1] = max - 1;
        anagramCounts[recordNumber - 1] = 1;
        System.arraycopy(wordIn, 0, anagrams[recordNumber - 1][0], 0, MAX_WORD_LENGTH);
    }

    private static void updateRecord(int index) {
        anagramCounts[index]++;
        System.arraycopy(wordIn, 0, anagrams[index][anagramCounts[index] - 1], 0, MAX_WORD_LENGTH);
    }

    private static void sortWordTable() {
        Arrays.sort(wordTable, 0, recordNumber, (a, b) -> {
            for (int i = 0; i < MAX_WORD_LENGTH; i++) {
                if (a[i] != b[i]) {
                    return b[i] - a[i];
                }
            }
            return 0;
        });
    }

    private static void findDeranged() {
        for (i = 0; i < recordNumber && !found; i++) {
            for (j = 0; j < anagramCounts[i] - 1 && !found; j++) {
                beg = j + 1;
                for (k = beg; k < anagramCounts[i] && !found; k++) {
                    System.arraycopy(anagrams[i][j], 0, wordTemp1, 0, MAX_WORD_LENGTH);
                    System.arraycopy(anagrams[i][k], 0, wordTemp2, 0, MAX_WORD_LENGTH);
                    checkDeranged();
                }
            }
        }
    }

    private static void checkDeranged() {
        exit = false;
        for (l = 0; l < wordLengths[i] && !exit; l++) {
            if (wordTemp1[l] == wordTemp2[l]) {
                exit = true;
            }
        }
        if (!exit) {
            System.out.print(new String(wordTemp1, 0, wordLengths[i]) + " " + new String(wordTemp2));
            found = true;
        }
    }

    private static void terminate() {
        System.out.println("\nRECORDS READ: " + totalRecords);
        System.out.println("RECORDS SELECTED " + selectedRecords);
        System.out.println("RECORD KEYS: " + recordNumber);
    }
}