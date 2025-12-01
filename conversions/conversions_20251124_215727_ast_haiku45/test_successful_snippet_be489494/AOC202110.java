import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AOC202110 {
    private int fileStatus = 0;
    private int recLen = 0;
    private char[] wsStack = new char[100];
    private long wsResult = 0;
    private int s = 0;
    private int i = 1;
    private int wrong = 0;
    private char x = ' ';
    private char y = ' ';

    public static void main(String[] args) {
        AOC202110 program = new AOC202110();
        program.run();
    }

    private void run() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("d10.input"));
            for (String line : lines) {
                processRecord(line);
            }
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processRecord(String inputRecord) {
        s = 0;
        wrong = 0;
        recLen = inputRecord.length();

        for (i = 0; i < recLen && wrong == 0; i++) {
            x = inputRecord.charAt(i);

            if (x == '(' || x == '[' || x == '{' || x == '<') {
                wsStack[s] = x;
                s++;
            } else {
                y = wsStack[s - 1];
                s--;

                if (x == ')' && y != '(') {
                    wsResult += 3;
                    wrong = 1;
                } else if (x == ']' && y != '[') {
                    wsResult += 57;
                    wrong = 1;
                } else if (x == '}' && y != '{') {
                    wsResult += 1197;
                    wrong = 1;
                } else if (x == '>' && y != '<') {
                    wsResult += 25137;
                    wrong = 1;
                }
            }
        }
    }
}