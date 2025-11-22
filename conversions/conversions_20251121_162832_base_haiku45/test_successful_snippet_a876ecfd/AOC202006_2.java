import java.io.*;
import java.nio.file.*;

public class AOC202006_2 {
    private static int[] wsGroupAnswers = new int[26];
    private static int groupSize = 0;
    private static int groupTotal = 0;
    private static int total = 0;

    public static void main(String[] args) {
        initVariables();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d6.input"));
            String line;
            while ((line = reader.readLine()) != null) {
                processRecord(line);
            }
            reader.close();
            nextGroup();
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initVariables() {
        for (int i = 0; i < 26; i++) {
            wsGroupAnswers[i] = 0;
        }
        groupSize = 0;
        groupTotal = 0;
    }

    private static void processRecord(String line) {
        int recLen = line.length();
        if (recLen == 0) {
            nextGroup();
        } else {
            processRow(line, recLen);
        }
    }

    private static void processRow(String line, int recLen) {
        groupSize++;
        for (int i = 0; i < recLen; i++) {
            char wsChar = line.charAt(i);
            int c = (int) wsChar;
            int x = wsGroupAnswers[c - 97] + 1;
            wsGroupAnswers[c - 97] = x;
        }
    }

    private static void nextGroup() {
        if (groupSize > 0) {
            tallyGroupTotal();
        }
        total += groupTotal;
        initVariables();
    }

    private static void tallyGroupTotal() {
        for (int i = 0; i < 26; i++) {
            if (wsGroupAnswers[i] == groupSize) {
                groupTotal++;
            }
        }
    }
}