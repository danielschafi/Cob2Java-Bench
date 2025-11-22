import java.io.*;
import java.util.*;

public class AOC2021102 {
    private static final int MAX_STACK_SIZE = 100;
    private static final int MAX_SCORES = 100;

    private int fileStatus = 0;
    private int recLen = 0;
    private char[] wsStack = new char[MAX_STACK_SIZE];
    private long wsResult = 0;
    private char[] wsLine = new char[128];
    private long wsLineScore = 0;
    private long[] wsScores = new long[MAX_SCORES];
    private int s = 0;
    private int i = 1;
    private int wrong = 0;
    private char x;
    private char y;
    private int n;
    private int scoresNum = 0;

    public static void main(String[] args) {
        AOC2021102 program = new AOC2021102();
        program.run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("d10.input"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                line.getChars(0, recLen, wsLine, 0);
                processRecord();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        findMiddleScore();
        System.out.println(wsResult);
    }

    private void processRecord() {
        s = 0;
        wrong = 0;
        for (i = 0; i < recLen && wrong == 0; i++) {
            x = wsLine[i];
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                wsStack[s++] = x;
            } else {
                y = wsStack[--s];
                if (x == ')' && y != '(' ||
                    x == ']' && y != '[' ||
                    x == '}' && y != '{' ||
                    x == '>' && y != '<') {
                    wrong = 1;
                }
            }
        }
        if (wrong == 0) {
            completeLine();
        }
    }

    private void completeLine() {
        wsLineScore = 0;
        while (s > 0) {
            switch (wsStack[--s]) {
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
            wsLineScore = 5 * wsLineScore + n;
        }
        wsScores[scoresNum++] = wsLineScore;
    }

    private void findMiddleScore() {
        Arrays.sort(wsScores, 0, scoresNum);
        wsResult = wsScores[scoresNum / 2];
    }
}