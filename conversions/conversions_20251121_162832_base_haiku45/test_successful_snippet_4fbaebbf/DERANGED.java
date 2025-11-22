import java.io.*;
import java.util.*;

public class DERANGED {
    private static final int MAX_RECORDS = 24000;
    private static final int MAX_ANAGRAMS = 6;
    private static final int MAX_WORD_LEN = 22;

    private String wsEof = "N";
    private String wsFnd = "N";
    private String wsExit = "N";

    private int wsTotRecs = 0;
    private int wsSelRecs = 0;
    private int wtRecNbr = 0;

    private String wsWordTemp;
    private String wsWordIn;
    private String wsWordKey;

    private List<WordRecord> wtRecord = new ArrayList<>();

    private String wsWordTemp1;
    private String wsWordTemp2;

    private int wsI, wsJ, wsK, wsL, wsBeg, wsMax;

    static class WordRecord {
        String wtWordKey;
        int wtWordLen;
        int wtAnagramCnt;
        String[] wtAnagrams;

        WordRecord() {
            wtAnagrams = new String[MAX_ANAGRAMS];
            for (int i = 0; i < MAX_ANAGRAMS; i++) {
                wtAnagrams[i] = "";
            }
            wtAnagramCnt = 0;
        }
    }

    public static void main(String[] args) {
        DERANGED program = new DERANGED();
        program.run();
    }

    private void run() {
        initialize();
        while (wsEof.equals("N")) {
            processRecord();
        }
        sortRecords();
        findDeranged();
        terminate();
    }

    private void initialize() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Anagrams.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                wsWordIn = line;
                readRecord();
            }
            wsEof = "Y";
        } catch (IOException e) {
            wsEof = "Y";
        }
    }

    private void readRecord() {
        wsTotRecs++;
    }

    private void processRecord() {
        if (isAlphabetic(wsWordIn)) {
            wsSelRecs++;
            wsWordTemp = wsWordIn;
            sortWord();
            wsWordKey = wsWordTemp;
            addToTable();
        }
        wsEof = "Y";
    }

    private boolean isAlphabetic(String word) {
        if (word == null || word.isEmpty()) return false;
        for (char c : word.toCharArray()) {
            if (c == ' ') break;
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private void sortWord() {
        char[] letters = wsWordTemp.toCharArray();
        wsMax = 0;
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == ' ') break;
            wsMax++;
        }

        for (wsI = 0; wsI < wsMax; wsI++) {
            for (wsJ = wsI + 1; wsJ < wsMax; wsJ++) {
                if (letters[wsJ] < letters[wsI]) {
                    char temp = letters[wsI];
                    letters[wsI] = letters[wsJ];
                    letters[wsJ] = temp;
                }
            }
        }

        wsWordTemp = new String(letters);
    }

    private void addToTable() {
        boolean found = false;
        for (WordRecord rec : wtRecord) {
            if (rec.wtWordKey.equals(wsWordKey)) {
                updateRecord(rec);
                found = true;
                break;
            }
        }
        if (!found) {
            addNewRecord();
        }
    }

    private void addNewRecord() {
        WordRecord rec = new WordRecord();
        rec.wtWordKey = wsWordKey;
        rec.wtWordLen = wsMax;
        rec.wtAnagramCnt = 1;
        rec.wtAnagrams[0] = wsWordIn;
        wtRecord.add(rec);
        wtRecNbr++;
    }

    private void updateRecord(WordRecord rec) {
        if (rec.wtAnagramCnt < MAX_ANAGRAMS) {
            rec.wtAnagrams[rec.wtAnagramCnt] = wsWordIn;
            rec.wtAnagramCnt++;
        }
    }

    private void sortRecords() {
        wtRecord.sort((a, b) -> Integer.compare(b.wtWordLen, a.wtWordLen));
    }

    private void findDeranged() {
        for (wsI = 0; wsI < wtRecord.size() && wsFnd.equals("N"); wsI++) {
            WordRecord recI = wtRecord.get(wsI);
            for (wsJ = 0; wsJ < recI.wtAnagramCnt - 1 && wsFnd.equals("N"); wsJ++) {
                wsBeg = wsJ + 1;
                for (wsK = wsBeg; wsK < recI.wtAnagramCnt && wsFnd.equals("N"); wsK++) {
                    wsWordTemp1 = recI.wtAnagrams[wsJ];
                    wsWordTemp2 = recI.wtAnagrams[wsK];
                    checkDeranged(recI);
                }
            }
        }
    }

    private void checkDeranged(WordRecord rec) {
        wsExit = "N";
        for (wsL = 0; wsL < rec.wtWordLen && wsExit.equals("N"); wsL++) {
            if (wsWordTemp1.charAt(wsL) == wsWordTemp2.charAt(wsL)) {
                wsExit = "Y";
            }
        }
        if (wsExit.equals("N")) {
            System.out.println(wsWordTemp1.substring(0, rec.wtWordLen) + " " + wsWordTemp2.substring(0, rec.wtWordLen));
            wsFnd = "Y";
        }
    }

    private void terminate() {
        System.out.println("RECORDS READ: " + wsTotRecs);
        System.out.println("RECORDS SELECTED " + wsSelRecs);
        System.out.println("RECORD KEYS: " + wtRecNbr);
    }
}