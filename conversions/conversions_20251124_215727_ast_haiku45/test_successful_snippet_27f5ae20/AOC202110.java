import java.io.*;
import java.util.*;

public class AOC202110 {
    private static final int MAX_STACK = 100;
    private static final int MAX_SCORES = 100;
    private static final int MAX_LINE = 128;
    
    private char[] wsStack = new char[MAX_STACK];
    private long[] wsScores = new long[MAX_SCORES];
    private String wsLine;
    private long wsLineScore;
    private long wsResult = 0;
    private int s = 0;
    private int i = 1;
    private int wrong = 0;
    private char x;
    private char y;
    private int n;
    private int scoresNum = 0;
    
    public static void main(String[] args) {
        AOC202110 program = new AOC202110();
        program.run();
    }
    
    private void run() {
        try {
            processFile("d10.input");
            findMiddleScore();
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void processFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            processRecord(line);
        }
        reader.close();
    }
    
    private void processRecord(String inputRecord) {
        wsLine = inputRecord;
        s = 0;
        wrong = 0;
        int recLen = inputRecord.length();
        
        for (i = 0; i < recLen && wrong == 0; i++) {
            x = inputRecord.charAt(i);
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                wsStack[s] = x;
                s++;
            } else {
                s--;
                y = wsStack[s];
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
        wsLineScore = 0;
        while (s > 0) {
            s--;
            char stackChar = wsStack[s];
            switch (stackChar) {
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
                default:
                    n = 0;
            }
            wsLineScore = 5 * wsLineScore + n;
        }
        wsScores[scoresNum] = wsLineScore;
        scoresNum++;
    }
    
    private void findMiddleScore() {
        Arrays.sort(wsScores, 0, scoresNum);
        int middleIndex = scoresNum / 2;
        wsResult = wsScores[scoresNum - 1 - middleIndex];
    }
}