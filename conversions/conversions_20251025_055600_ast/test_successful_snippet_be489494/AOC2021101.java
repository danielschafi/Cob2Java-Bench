import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021101 {
    public static void main(String[] args) {
        String inputFile = "d10.input";
        int fileStatus = 0;
        int recLen;
        char[] wsStack = new char[100];
        long wsResult = 0;
        int s = 0;
        int i;
        int wrong = 0;
        char x;
        char y;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                recLen = inputRecord.length();
                s = 0;
                wrong = 0;
                for (i = 0; i < recLen && wrong == 0; i++) {
                    x = inputRecord.charAt(i);
                    if (x == '(' || x == '[' || x == '{' || x == '<') {
                        wsStack[s++] = x;
                    } else {
                        y = wsStack[--s];
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
        } catch (IOException e) {
            fileStatus = 1;
        }

        System.out.println(wsResult);
    }
}