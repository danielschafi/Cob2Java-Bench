import java.io.*;
import java.util.*;

public class AOC202110Part2 {
    private static final String INPUT_FILE = "d10.input";
    private static int[] wsStack = new int[100];
    private static long wsResult = 0;
    private static long wsLineScore = 0;
    private static long[] wsScores = new long[100];
    private static int s = 0;
    private static int i = 0;
    private static int wrong = 0;
    private static char x;
    private static char y;
    private static int n = 0;
    private static int scoresNum = 0;

    public static void main(String[] args) {
        readFile();
        findMiddleScore();
        System.out.println(wsResult);
    }

    private static void readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                processRecord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processRecord(String line) {
        s = 0;
        wrong = 0;

        for (i = 0; i < line.length() && wrong == 0; i++) {
            x = line.charAt(i);
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                wsStack[s] = x;
                s++;
            } else {
                y = (char) wsStack[s - 1];
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

    private static void completeLine() {
        wsLineScore = 0;
        while (s > 0) {
            char stackChar = (char) wsStack[s - 1];
            switch (stackChar) {
                case '(':
                    n = 1;
                    break;
                case '[':
                    n = 2;
                    break;
                case '{':
                    n = 3;
                    break;
                case '<':
                    n = 4;
                    break;
            }
            s--;
            wsLineScore = 5 * wsLineScore + n;
        }
        wsScores[scoresNum] = wsLineScore;
        scoresNum++;
    }

    private static void findMiddleScore() {
        Arrays.sort(wsScores, 0, scoresNum);
        int middleIndex = scoresNum / 2;
        wsResult = wsScores[middleIndex];
    }
}