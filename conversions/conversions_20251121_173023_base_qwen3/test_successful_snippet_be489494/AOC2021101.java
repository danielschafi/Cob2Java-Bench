import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021101 {
    private static final String INPUT_FILE = "d10.input";
    private static final int STACK_SIZE = 100;
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int result = 0;
            
            while ((line = reader.readLine()) != null) {
                int stackPointer = 0;
                boolean wrong = false;
                char[] stack = new char[STACK_SIZE];
                
                for (int i = 0; i < line.length() && !wrong; i++) {
                    char x = line.charAt(i);
                    
                    if (x == '(' || x == '[' || x == '{' || x == '<') {
                        stack[stackPointer++] = x;
                    } else {
                        if (stackPointer > 0) {
                            char y = stack[--stackPointer];
                            
                            if (x == ')' && y != '(') {
                                result += 3;
                                wrong = true;
                            } else if (x == ']' && y != '[') {
                                result += 57;
                                wrong = true;
                            } else if (x == '}' && y != '{') {
                                result += 1197;
                                wrong = true;
                            } else if (x == '>' && y != '<') {
                                result += 25137;
                                wrong = true;
                            }
                        } else {
                            // This shouldn't happen with valid input
                            wrong = true;
                        }
                    }
                }
            }
            
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}