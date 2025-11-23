import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC2021102 {
    private static final String INPUT_FILE = "d10.input";
    private static final int STACK_SIZE = 100;
    private static final int MAX_SCORES = 100;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            char[] stack = new char[STACK_SIZE];
            long[] scores = new long[MAX_SCORES];
            int scoresCount = 0;

            while ((line = reader.readLine()) != null) {
                char[] chars = line.toCharArray();
                int stackPointer = 0;
                boolean wrong = false;

                for (char c : chars) {
                    if (c == '(' || c == '[' || c == '{' || c == '<') {
                        stack[stackPointer++] = c;
                    } else {
                        if (stackPointer > 0) {
                            char top = stack[--stackPointer];
                            if ((c == ')' && top != '(') ||
                                (c == ']' && top != '[') ||
                                (c == '}' && top != '{') ||
                                (c == '>' && top != '<')) {
                                wrong = true;
                                break;
                            }
                        } else {
                            wrong = true;
                            break;
                        }
                    }
                }

                if (!wrong && stackPointer > 0) {
                    long lineScore = 0;
                    while (stackPointer > 0) {
                        char c = stack[--stackPointer];
                        int n;
                        switch (c) {
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
                            default:
                                n = 0;
                                break;
                        }
                        lineScore = 5 * lineScore + n;
                    }
                    scores[scoresCount++] = lineScore;
                }
            }

            Arrays.sort(scores, 0, scoresCount);
            long result = scores[(scoresCount - 1) / 2];
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}