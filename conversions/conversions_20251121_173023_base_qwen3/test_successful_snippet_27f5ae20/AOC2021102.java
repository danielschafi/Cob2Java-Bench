import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class AOC2021102 {
    private static final String INPUT_FILE = "d10.input";
    private static final int MAX_STACK_SIZE = 100;
    private static final int MAX_LINES = 1000;
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            ArrayList<Long> scores = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                long score = processLine(line);
                if (score != -1) {
                    scores.add(score);
                }
            }
            
            Collections.sort(scores);
            long result = scores.get(scores.size() / 2);
            System.out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static long processLine(String input) {
        char[] stack = new char[MAX_STACK_SIZE];
        int stackPointer = 0;
        boolean wrong = false;
        
        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                stack[stackPointer++] = x;
            } else {
                if (stackPointer == 0) {
                    wrong = true;
                    break;
                }
                
                char y = stack[--stackPointer];
                
                if ((x == ')' && y != '(') ||
                    (x == ']' && y != '[') ||
                    (x == '}' && y != '{') ||
                    (x == '>' && y != '<')) {
                    wrong = true;
                    break;
                }
            }
        }
        
        if (wrong) {
            return -1;
        }
        
        long lineScore = 0;
        while (stackPointer > 0) {
            char c = stack[--stackPointer];
            int n = 0;
            
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
            }
            
            lineScore = 5 * lineScore + n;
        }
        
        return lineScore;
    }
}