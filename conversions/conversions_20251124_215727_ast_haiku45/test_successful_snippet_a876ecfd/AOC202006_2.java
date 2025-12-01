import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202006_2 {
    private static int fileStatus = 0;
    private static int recLen = 0;
    private static int[] wsGroupAnswers = new int[26];
    private static char wsChar;
    
    private static int i = 1;
    private static int c = 1;
    private static int x = 1;
    private static int groupSize = 0;
    private static int groupTotal = 0;
    private static int total = 0;
    
    private static String inputRecord = "";
    private static BufferedReader reader;

    public static void main(String[] args) {
        try {
            initVariables();
            openInputFile();
            while (fileStatus == 0) {
                readFile();
            }
            closeInputFile();
            nextGroup();
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openInputFile() throws IOException {
        reader = new BufferedReader(new FileReader("d6.input"));
    }

    private static void closeInputFile() throws IOException {
        if (reader != null) {
            reader.close();
        }
    }

    private static void readFile() throws IOException {
        String line = reader.readLine();
        if (line == null) {
            fileStatus = 1;
        } else {
            inputRecord = line;
            recLen = inputRecord.length();
            processRecord();
        }
    }

    private static void processRecord() {
        if (recLen == 0) {
            nextGroup();
        } else {
            processRow();
        }
    }

    private static void initVariables() {
        for (i = 1; i <= 26; i++) {
            wsGroupAnswers[i - 1] = 0;
        }
        groupSize = 0;
        groupTotal = 0;
    }

    private static void nextGroup() {
        if (groupSize > 0) {
            tallyGroupTotal();
        }
        total += groupTotal;
        initVariables();
    }

    private static void processRow() {
        groupSize++;
        for (i = 1; i <= recLen; i++) {
            wsChar = inputRecord.charAt(i - 1);
            c = (int) wsChar;
            x = wsGroupAnswers[c - 97] + 1;
            wsGroupAnswers[c - 97] = x;
        }
    }

    private static void tallyGroupTotal() {
        for (i = 1; i <= 26; i++) {
            if (wsGroupAnswers[i - 1] == groupSize) {
                groupTotal++;
            }
        }
    }
}