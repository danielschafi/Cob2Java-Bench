import java.io.*;
import java.util.*;

public class DERANGED {
    
    static class WordRecord implements Comparable<WordRecord> {
        String wordKey;
        int wordLen;
        int anagramCnt;
        String[] anagrams;
        
        WordRecord() {
            anagrams = new String[6];
            anagramCnt = 0;
        }
        
        @Override
        public int compareTo(WordRecord other) {
            return Integer.compare(other.wordLen, this.wordLen);
        }
    }
    
    static boolean wsEof = false;
    static boolean wsFnd = false;
    static int wsTotRecs = 0;
    static int wsSelRecs = 0;
    static List<WordRecord> wordTable = new ArrayList<>();
    
    public static void main(String[] args) {
        initialize();
        while (!wsEof) {
            processRecord();
        }
        Collections.sort(wordTable);
        findDeranged();
        terminate();
    }
    
    static BufferedReader reader;
    static String wsWordIn;
    
    static void initialize() {
        try {
            reader = new BufferedReader(new FileReader("Anagrams.txt"));
            readRecord();
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
            System.exit(1);
        }
    }
    
    static void readRecord() {
        try {
            wsWordIn = reader.readLine();
            if (wsWordIn == null) {
                wsEof = true;
            } else {
                wsTotRecs++;
                if (wsWordIn.length() < 22) {
                    wsWordIn = String.format("%-22s", wsWordIn);
                } else if (wsWordIn.length() > 22) {
                    wsWordIn = wsWordIn.substring(0, 22);
                }
            }
        } catch (IOException e) {
            wsEof = true;
        }
    }
    
    static void processRecord() {
        if (isAlphabetic(wsWordIn.trim())) {
            wsSelRecs++;
            String sortedWord = sortWord(wsWordIn);
            addToTable(sortedWord, wsWordIn);
        }
        readRecord();
    }
    
    static boolean isAlphabetic(String str) {
        if (str.isEmpty()) return false;
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }
    
    static String sortWord(String word) {
        char[] chars = (word + " ").toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                max = i;
                break;
            }
        }
        
        for (int i = 0; i < max - 1; i++) {
            for (int j = i + 1; j < max; j++) {
                if (chars[j] < chars[i]) {
                    char temp = chars[i];
                    chars[i] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        
        return new String(chars, 0, max);
    }
    
    static void addToTable(String wordKey, String wordIn) {
        boolean found = false;
        for (WordRecord record : wordTable) {
            if (record.wordKey.equals(wordKey)) {
                updateRecord(record, wordIn);
                found = true;
                break;
            }
        }
        
        if (!found) {
            addRecord(wordKey, wordIn);
        }
    }
    
    static void addRecord(String wordKey, String wordIn) {
        WordRecord record = new WordRecord();
        record.wordKey = wordKey;
        record.wordLen = wordKey.length();
        record.anagramCnt = 1;
        record.anagrams[0] = wordIn;
        wordTable.add(record);
    }
    
    static void updateRecord(WordRecord record, String wordIn) {
        if (record.anagramCnt < 6) {
            record.anagrams[record.anagramCnt] = wordIn;
            record.anagramCnt++;
        }
    }
    
    static void findDeranged() {
        for (int i = 0; i < wordTable.size() && !wsFnd; i++) {
            WordRecord record = wordTable.get(i);
            for (int j = 0; j < record.anagramCnt - 1 && !wsFnd; j++) {
                for (int k = j + 1; k < record.anagramCnt && !wsFnd; k++) {
                    String word1 = record.anagrams[j];
                    String word2 = record.anagrams[k];
                    checkDeranged(word1, word2, record.wordLen);
                }
            }
        }
    }
    
    static void checkDeranged(String word1, String word2, int len) {
        boolean isDeranged = true;
        for (int l = 0; l < len; l++) {
            if (word1.charAt(l) == word2.charAt(l)) {
                isDeranged = false;
                break;
            }
        }
        
        if (isDeranged) {
            System.out.println(word1.substring(0, len) + " " + word2.trim());
            wsFnd = true;
        }
    }
    
    static void terminate() {
        System.out.println("RECORDS READ: " + wsTotRecs);
        System.out.println("RECORDS SELECTED " + wsSelRecs);
        System.out.println("RECORD KEYS: " + wordTable.size());
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing file: " + e.getMessage());
        }
    }
}