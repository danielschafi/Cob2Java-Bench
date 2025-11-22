import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AOC202110Part2 {
    public static void main(String[] args) {
        List<Long> scores = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("d10.input"))) {
            String line;
            while ((line = br.readLine()) != null) {
                long score = processLine(line);
                if (score > 0) {
                    scores.add(score);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        Collections.sort(scores, Collections.reverseOrder());
        long result = scores.get(scores.size() / 2);
        System.out.println(result);
    }
    
    private static long processLine(String line) {
        char[] stack = new char[100];
        int stackPointer = 0;
        boolean wrong = false;
        
        for (int i = 0; i < line.length() && !wrong; i++) {
            char x = line.charAt(i);
            
            if (x == '(' || x == '[' || x == '{' || x == '<') {
                stack[stackPointer] = x;
                stackPointer++;
            } else {
                stackPointer--;
                char y = stack[stackPointer];
                
                if (x == ')' && y != '(') {
                    wrong = true;
                } else if (x == ']' && y != '[') {
                    wrong = true;
                } else if (x == '}' && y != '{') {
                    wrong = true;
                } else if (x == '>' && y != '<') {
                    wrong = true;
                }
            }
        }
        
        if (wrong) {
            return 0;
        }
        
        long lineScore = 0;
        while (stackPointer > 0) {
            stackPointer--;
            char c = stack[stackPointer];
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