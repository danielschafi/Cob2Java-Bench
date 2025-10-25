import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021101 {
    private static final String INPUTFILE = "d10.input";
    private static final int MAX_RECORD_LENGTH = 128;
    private static char[] inputRecord = new char[MAX_RECORD_LENGTH];
    private static int fileStatus = 0;
    private static int recLen;
    private static char[] wsStack = new char[100];
    private static long wsResult = 0;
    private static int s;
    private static int i;
    private static int wrong;
    private static char x;
    private static char y;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUTFILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recLen = line.length();
                line.getChars(0, recLen, inputRecord, 0);
                processRecord();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(wsResult);
    }

    private static void processRecord() {
        s = 0;
        wrong = 0;
        for (i = 0; i < recLen && wrong == 0; i++) {
            x = inputRecord[i];
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                s++;
                wsStack[s - 1] = x;
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