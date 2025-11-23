import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DERANGED {
    static class WordRecord {
        String wordKey;
        int wordLen;
        int anagramCnt;
        String[] anagrams;

        public WordRecord() {
            anagrams = new String[6];
        }
    }

    static final int MAX_WORDS = 24000;
    static WordRecord[] wtRecord = new WordRecord[MAX_WORDS];
    static int wtRecNbr = 0;
    static boolean wsFnd = false;
    static boolean wsExit = false;
    static int wsTotRecs = 0;
    static int wsSelRecs = 0;
    static char[] wsLetter = new char[23];
    static char[] wsLetter1 = new char[22];
    static char[] wsLetter2 = new char[22];
    static String wsWordTemp = "";
    static String wsWordTemp1 = "";
    static String wsWordTemp2 = "";
    static String wsWordIn = "";
    static String wsWordKey = "";
    static char wsLetterHld = '\0';
    static int wsI = 0;
    static int wsJ = 0;
    static int wsK = 0;
    static int wsL = 0;
    static int wsBeg = 0;
    static int wsMax = 0;

    public static void main(String[] args) {
        initialize();
        processRecord();
        sortRecords();
        findDeranged();
        terminate();
    }

    static void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Anagrams.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wsWordIn = line;
                if (isAlpha(wsWordIn)) {
                    wsSelRecs++;
                    wsWordTemp = wsWordIn + " ";
                    sortWord();
                    wsWordKey = wsWordTemp;
                    addToTable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isAlpha(String s) {
        return s.matches("[a-zA-Z]+");
    }

    static void sortWord() {
        wsMax = 0;
        while (wsMax < wsWordTemp.length() && wsWordTemp.charAt(wsMax) != ' ') {
            wsMax++;
        }

        for (int i = 0; i < wsMax; i++) {
            for (int j = i + 1; j < wsMax; j++) {
                if (wsWordTemp.charAt(i) > wsWordTemp.charAt(j)) {
                    char temp = wsWordTemp.charAt(i);
                    wsWordTemp = wsWordTemp.substring(0, i) + wsWordTemp.charAt(j) + wsWordTemp.substring(i + 1);
                    wsWordTemp = wsWordTemp.substring(0, j) + temp + wsWordTemp.substring(j + 1);
                }
            }
        }
    }

    static void addToTable() {
        int idx = 0;
        boolean found = false;
        for (int i = 0; i < wtRecNbr; i++) {
            if (wtRecord[i].wordKey.equals(wsWordKey)) {
                idx = i;
                found = true;
                break;
            }
        }
        if (!found) {
            addRecord();
        } else {
            updateRecord(idx);
        }
    }

    static void addRecord() {
        wtRecNbr++;
        wtRecord[wtRecNbr - 1] = new WordRecord();
        wtRecord[wtRecNbr - 1].wordKey = wsWordKey;
        wtRecord[wtRecNbr - 1].wordLen = wsMax - 1;
        wtRecord[wtRecNbr - 1].anagramCnt = 1;
        wtRecord[wtRecNbr - 1].anagrams[0] = wsWordIn;
    }

    static void updateRecord(int idx) {
        wtRecord[idx].anagramCnt++;
        wtRecord[idx].anagrams[wtRecord[idx].anagramCnt - 1] = wsWordIn;
    }

    static void sortRecords() {
        Arrays.sort(wtRecord, 0, wtRecNbr, (a, b) -> Integer.compare(b.wordLen, a.wordLen));
    }

    static void findDeranged() {
        for (int i = 0; i < wtRecNbr && !wsFnd; i++) {
            for (int j = 0; j < wtRecord[i].anagramCnt - 1 && !wsFnd; j++) {
                wsBeg = j + 1;
                for (int k = wsBeg; k < wtRecord[i].anagramCnt && !wsFnd; k++) {
                    wsWordTemp1 = wtRecord[i].anagrams[j];
                    wsWordTemp2 = wtRecord[i].anagrams[k];
                    checkDeranged();
                }
            }
        }
    }

    static void checkDeranged() {
        wsExit = false;
        for (int l = 0; l < wtRecord[wsI].wordLen && !wsExit; l++) {
            if (wsWordTemp1.charAt(l) == wsWordTemp2.charAt(l)) {
                wsExit = true;
            }
        }
        if (!wsExit) {
            System.out.println(wsWordTemp1.substring(0, wtRecord[wsI].wordLen) + " " + wsWordTemp2);
            wsFnd = true;
        }
    }

    static void terminate() {
        System.out.println("RECORDS READ: " + wsTotRecs);
        System.out.println("RECORDS SELECTED " + wsSelRecs);
        System.out.println("RECORD KEYS: " + wtRecNbr);
    }
}