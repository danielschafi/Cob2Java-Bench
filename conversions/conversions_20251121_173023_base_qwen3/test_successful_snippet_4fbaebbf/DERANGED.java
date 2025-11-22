import java.io.*;
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
    static boolean wsEof = false;
    static boolean wsFnd = false;
    static boolean wsExit = false;
    static int wsTotRecs = 0;
    static int wsSelRecs = 0;
    static char[] wsLetter = new char[23];
    static char wsLetterHld;
    static String wsWordIn = "";
    static String wsWordKey = "";
    static char[] wsLetter1 = new char[22];
    static char[] wsLetter2 = new char[22];
    static int wsI, wsJ, wsK, wsL, wsBeg, wsMax;
    
    public static void main(String[] args) {
        initialize();
        processRecord();
        sortWords();
        findDeranged();
        terminate();
    }
    
    static void initialize() {
        try {
            Scanner scanner = new Scanner(new File("Anagrams.txt"));
            while (scanner.hasNextLine()) {
                wsWordIn = scanner.nextLine().trim();
                if (wsWordIn.length() == 0) continue;
                if (wsWordIn.matches("[a-zA-Z]+")) {
                    wsSelRecs++;
                    wsWordIn = wsWordIn.toLowerCase();
                    wsWordKey = wsWordIn;
                    sortWord();
                    wsWordKey = new String(wsLetter, 0, wsMax - 1);
                    addToTable();
                }
                wsTotRecs++;
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
    }
    
    static void sortWord() {
        wsMax = 0;
        for (int i = 0; i < 23; i++) {
            if (wsWordIn.charAt(i) == '\0') break;
            wsLetter[i] = wsWordIn.charAt(i);
            wsMax++;
        }
        
        for (int i = 0; i < wsMax - 1; i++) {
            for (int j = i + 1; j < wsMax; j++) {
                if (wsLetter[i] > wsLetter[j]) {
                    wsLetterHld = wsLetter[i];
                    wsLetter[i] = wsLetter[j];
                    wsLetter[j] = wsLetterHld;
                }
            }
        }
    }
    
    static void addToTable() {
        for (int i = 0; i < wtRecNbr; i++) {
            if (wtRecord[i].wordKey.equals(wsWordKey)) {
                updateRecord(i);
                return;
            }
        }
        addRecord();
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
    
    static void sortWords() {
        Arrays.sort(wtRecord, 0, wtRecNbr, (a, b) -> Integer.compare(b.wordLen, a.wordLen));
    }
    
    static void findDeranged() {
        for (int i = 0; i < wtRecNbr && !wsFnd; i++) {
            for (int j = 0; j < wtRecord[i].anagramCnt - 1 && !wsFnd; j++) {
                wsBeg = j + 1;
                for (int k = wsBeg; k < wtRecord[i].anagramCnt && !wsFnd; k++) {
                    String word1 = wtRecord[i].anagrams[j];
                    String word2 = wtRecord[i].anagrams[k];
                    checkDeranged(word1, word2);
                }
            }
        }
    }
    
    static void checkDeranged(String word1, String word2) {
        wsExit = false;
        int len = Math.min(word1.length(), word2.length());
        for (int l = 0; l < len && !wsExit; l++) {
            if (word1.charAt(l) == word2.charAt(l)) {
                wsExit = true;
            }
        }
        if (!wsExit) {
            System.out.println(word1 + " " + word2);
            wsFnd = true;
        }
    }
    
    static void terminate() {
        System.out.println("RECORDS READ: " + wsTotRecs);
        System.out.println("RECORDS SELECTED " + wsSelRecs);
        System.out.println("RECORD KEYS: " + wtRecNbr);
    }
}