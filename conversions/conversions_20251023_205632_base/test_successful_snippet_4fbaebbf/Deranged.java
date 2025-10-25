import java.io.*;
import java.util.*;

public class Deranged {
    private static final int MAX_RECORDS = 24000;
    private static final int MAX_WORD_LENGTH = 22;

    private static char[][] wtRecord = new char[MAX_RECORDS][MAX_WORD_LENGTH];
    private static int[] wtWordLen = new int[MAX_RECORDS];
    private static int[] wtAnagramCnt = new int[MAX_RECORDS];
    private static char[][][] wtAnagrams = new char[MAX_RECORDS][6][MAX_WORD_LENGTH];

    private static int wtRecNbr = 0;
    private static int wsTotRecs = 0;
    private static int wsSelRecs = 0;
    private static char wsEof = 'N';
    private static char wsFnd = 'N';

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Anagrams.txt"))) {
            initialize(reader);
            processRecords(reader);
            sortRecords();
            findDeranged();
            terminate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initialize(BufferedReader reader) throws IOException {
        readRecord(reader);
    }

    private static void readRecord(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        if (line == null) {
            wsEof = 'Y';
        } else {
            wsTotRecs++;
            char[] wsWordIn = line.toCharArray();
            if (isAlphabetic(wsWordIn)) {
                wsSelRecs++;
                char[] wsWordTemp = Arrays.copyOf(wsWordIn, MAX_WORD_LENGTH + 1);
                sortWord(wsWordTemp);
                char[] wsWordKey = Arrays.copyOf(wsWordTemp, MAX_WORD_LENGTH);
                addToTable(wsWordKey, wsWordIn);
            }
        }
    }

    private static boolean isAlphabetic(char[] word) {
        for (char c : word) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    private static void sortWord(char[] word) {
        int wsMax = 0;
        while (wsMax < MAX_WORD_LENGTH && word[wsMax] != ' ') {
            wsMax++;
        }

        for (int wsI = 0; wsI < wsMax; wsI++) {
            for (int wsJ = wsI + 1; wsJ < wsMax; wsJ++) {
                if (word[wsJ] < word[wsI]) {
                    char temp = word[wsI];
                    word[wsI] = word[wsJ];
                    word[wsJ] = temp;
                }
            }
        }
    }

    private static void addToTable(char[] wsWordKey, char[] wsWordIn) {
        for (int wsIdx = 0; wsIdx < wtRecNbr; wsIdx++) {
            if (Arrays.equals(wtRecord[wsIdx], wsWordKey)) {
                wtAnagramCnt[wsIdx]++;
                wtAnagrams[wsIdx][wtAnagramCnt[wsIdx] - 1] = wsWordIn;
                return;
            }
        }
        wtRecord[wtRecNbr] = wsWordKey;
        wtWordLen[wtRecNbr] = wsWordKey.length;
        wtAnagramCnt[wtRecNbr] = 1;
        wtAnagrams[wtRecNbr][0] = wsWordIn;
        wtRecNbr++;
    }

    private static void sortRecords() {
        Arrays.sort(wtRecord, 0, wtRecNbr, (a, b) -> Integer.compare(b.length, a.length));
    }

    private static void findDeranged() {
        for (int wsI = 0; wsI < wtRecNbr && wsFnd != 'Y'; wsI++) {
            for (int wsJ = 0; wsJ < wtAnagramCnt[wsI] - 1 && wsFnd != 'Y'; wsJ++) {
                for (int wsK = wsJ + 1; wsK < wtAnagramCnt[wsI] && wsFnd != 'Y'; wsK++) {
                    char[] wsWordTemp1 = wtAnagrams[wsI][wsJ];
                    char[] wsWordTemp2 = wtAnagrams[wsI][wsK];
                    if (checkDeranged(wsWordTemp1, wsWordTemp2, wtWordLen[wsI])) {
                        System.out.println(new String(wsWordTemp1, 0, wtWordLen[wsI]) + " " + new String(wsWordTemp2));
                        wsFnd = 'Y';
                    }
                }
            }
        }
    }

    private static boolean checkDeranged(char[] wsWordTemp1, char[] wsWordTemp2, int length) {
        for (int wsL = 0; wsL < length; wsL++) {
            if (wsWordTemp1[wsL] == wsWordTemp2[wsL]) {
                return false;
            }
        }
        return true;
    }

    private static void terminate() {
        System.out.println("RECORDS READ: " + wsTotRecs);
        System.out.println("RECORDS SELECTED: " + wsSelRecs);
        System.out.println("RECORD KEYS: " + wtRecNbr);
    }

    private static void processRecords(BufferedReader reader) throws IOException {
        while (wsEof != 'Y') {
            readRecord(reader);
        }
    }
}