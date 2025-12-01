import java.io.*;
import java.util.*;

public class Deranged {
    static class WordRecord {
        String wordKey;
        int wordLen;
        List<String> anagrams;
        
        WordRecord(String key, int len) {
            this.wordKey = key;
            this.wordLen = len;
            this.anagrams = new ArrayList<>();
        }
    }
    
    static String wsEof = "N";
    static String wsFnd = "N";
    static String wsExit = "N";
    static int wsTotRecs = 0;
    static int wsSelRecs = 0;
    static int wtRecNbr = 0;
    static String wsWordIn = "";
    static String wsWordKey = "";
    static List<WordRecord> wtRecord = new ArrayList<>();
    static int wsI, wsJ, wsK, wsL, wsBeg, wsMax;
    
    public static void main(String[] args) {
        initialize();
        processRecords();
        sortRecords();
        findDeranged();
        terminate();
    }
    
    static void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Anagrams.txt"))) {
            readRecord(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void readRecord(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            wsWordIn = line.trim();
            wsTotRecs++;
            if (wsEof.equals("N")) {
                processRecord(reader);
            }
        }
        wsEof = "Y";
    }
    
    static void processRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Anagrams.txt"))) {
            String line;
            while ((line = reader.readLine()) != null && !wsEof.equals("Y")) {
                wsWordIn = line.trim();
                if (isAlphabetic(wsWordIn)) {
                    wsSelRecs++;
                    String sorted = sortWord(wsWordIn);
                    wsWordKey = sorted;
                    addToTable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void processRecord(BufferedReader reader) throws IOException {
        if (isAlphabetic(wsWordIn)) {
            wsSelRecs++;
            String sorted = sortWord(wsWordIn);
            wsWordKey = sorted;
            addToTable();
        }
    }
    
    static boolean isAlphabetic(String word) {
        return word.matches("[a-zA-Z]+");
    }
    
    static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
    
    static void addToTable() {
        boolean found = false;
        for (WordRecord rec : wtRecord) {
            if (rec.wordKey.equals(wsWordKey)) {
                rec.anagrams.add(wsWordIn);
                found = true;
                break;
            }
        }
        if (!found) {
            WordRecord newRec = new WordRecord(wsWordKey, wsWordKey.length());
            newRec.anagrams.add(wsWordIn);
            wtRecord.add(newRec);
        }
    }
    
    static void sortRecords() {
        wtRecord.sort((a, b) -> Integer.compare(b.wordLen, a.wordLen));
    }
    
    static void findDeranged() {
        for (wsI = 0; wsI < wtRecord.size() && wsFnd.equals("N"); wsI++) {
            WordRecord rec = wtRecord.get(wsI);
            for (wsJ = 0; wsJ < rec.anagrams.size() - 1 && wsFnd.equals("N"); wsJ++) {
                wsBeg = wsJ + 1;
                for (wsK = wsBeg; wsK < rec.anagrams.size() && wsFnd.equals("N"); wsK++) {
                    String word1 = rec.anagrams.get(wsJ);
                    String word2 = rec.anagrams.get(wsK);
                    checkDeranged(word1, word2, rec.wordLen);
                }
            }
        }
    }
    
    static void checkDeranged(String word1, String word2, int len) {
        wsExit = "N";
        for (wsL = 0; wsL < len && wsExit.equals("N"); wsL++) {
            if (word1.charAt(wsL) == word2.charAt(wsL)) {
                wsExit = "Y";
            }
        }
        if (wsExit.equals("N")) {
            System.out.println(word1 + " " + word2);
            wsFnd = "Y";
        }
    }
    
    static void terminate() {
        System.out.println("RECORDS READ: " + wsTotRecs);
        System.out.println("RECORDS SELECTED " + wsSelRecs);
        System.out.println("RECORD KEYS: " + wtRecord.size());
    }
}