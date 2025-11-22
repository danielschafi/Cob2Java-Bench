import java.io.*;
import java.nio.file.*;

public class AOC202006_1 {
    private static int fileStatus = 0;
    private static int[] wsGroupAnswers = new int[26];
    private static int groupTotal = 0;
    private static int total = 0;

    public static void main(String[] args) {
        nextGroup();
        
        try {
            String content = new String(Files.readAllBytes(Paths.get("d6.input")));
            String[] lines = content.split("\n");
            
            for (String line : lines) {
                processRecord(line);
            }
            
            nextGroup();
            System.out.println(total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String line) {
        int recLen = line.length();
        
        if (recLen == 0) {
            nextGroup();
        } else {
            processRow(line, recLen);
        }
    }

    private static void nextGroup() {
        total += groupTotal;
        
        for (int i = 0; i < 26; i++) {
            wsGroupAnswers[i] = 0;
        }
        
        groupTotal = 0;
    }

    private static void processRow(String line, int recLen) {
        for (int i = 0; i < recLen; i++) {
            char wsChar = line.charAt(i);
            int c = (int) wsChar;
            
            if (wsGroupAnswers[c - 97] == 0) {
                wsGroupAnswers[c - 97] = 1;
                groupTotal++;
            }
        }
    }
}