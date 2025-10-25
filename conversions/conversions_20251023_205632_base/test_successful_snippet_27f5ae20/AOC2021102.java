import java.io.*;
import java.util.*;

public class AOC2021102 {
    private static final int MAX_STACK_SIZE = 100;
    private static final int MAX_SCORES = 100;

    private char[] fileStack = new char[MAX_STACK_SIZE];
    private long[] scores = new long[MAX_SCORES];
    private int fileStatus = 0;
    private int recLen = 0;
    private int s = 0;
    private int wrong = 0;
    private char x;
    private char y;
    private int n;
    private int scoresNum = 0;

    public static void main(String[] args) {
        AOC2021102 program = new AOC2021102();
        program.execute();
    }

    private void execute() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d10.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        findMiddleScore();
        System.out.println(scores[scoresNum / 2]);
    }

    private void processRecord(String line) {
        s = 0;
        wrong = 0;
        for (int i = 0; i < recLen && wrong == 0; i++) {
            x = line.charAt(i);
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                s++;
                fileStack[s - 1] = x;
            } else {
                y = fileStack[s - 1];
                s--;
                if ((x == ')' && y != '(') ||
                    (x == ']' && y != '[') ||
                    (x == '}' && y != '{') ||
                    (x == '>' && y != '<')) {
                    wrong = 1;
                }
            }
        }
        if (wrong == 0) {
            completeLine();
        }
    }

    private void completeLine() {
        long lineScore = 0;
        while (s > 0) {
            y = fileStack[s - 1];
            s--;
            switch (y) {
                case '(':
                    x = ')';
                    n = 1;
                    break;
                case '[':
                    x = ']';
                    n = 2;
                    break;
                case '{':
                    x = '}';
                    n = 3;
                    break;
                case '<':
                    x = '>';
                    n = 4;
                    break;
            }
            lineScore = 5 * lineScore + n;
        }
        scores[scoresNum++] = lineScore;
    }

    private void findMiddleScore() {
        Arrays.sort(scores, 0, scoresNum);
        reverse(scores, 0, scoresNum - 1);
    }

    private void reverse(long[] array, int start, int end) {
        while (start < end) {
            long temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
}