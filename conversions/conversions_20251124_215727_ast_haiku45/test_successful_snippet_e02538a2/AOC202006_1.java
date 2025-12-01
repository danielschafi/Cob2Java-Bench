```java
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202006_1 {
    private static int fileStatus = 0;
    private static int recLen = 0;
    private static char[] wsGroupAnswers = new char[26];
    private static char wsChar;
    private static int i = 1;
    private static int c = 1;
    private static int groupTotal = 0;
    private static int total = 0;

    public static void main(String[] args) {
        try {
            nextGroup();
            readFile();
            nextGroup();
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() throws IOException {
        Path path = Paths.get("d6.input");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                processRecord(line);
            }
            fileStatus = 1;
        }
    }

    private static void processRecord(String line) {
        if (recLen == 0) {
            nextGroup();
        } else {
            processRow(line);
        }
    }

    private static void nextGroup() {
        total += groupTotal;
        for (i = 1; i <= 26; i++) {
            wsGroupAnswers[i - 1] = 0;
        }
        groupTotal = 0;
    }

    private static void processRow(String line) {
        for (i = 1; i <= recLen; i++) {
            wsChar = line.charAt(i - 1);
            c = (int) wsChar;
            if (wsGroupAnswers[c - 97] == 0) {
                wsGroupAnswers[c - 97] = 1;
                groupTotal++;
            }
        }
    }
}