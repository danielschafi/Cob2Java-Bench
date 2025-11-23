import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2021101 {
    private static final String INPUT_FILE = "d10.input";
    
    public static void main(String[] args) {
        int wsResult = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int s = 0;
                int wrong = 0;
                char[] wsStack = new char[100];
                
                for (int i = 0; i < line.length() && wrong == 0; i++) {
                    char x = line.charAt(i);
                    
                    if (x == '(' || x == '[' || x == '{' || x == '<') {
                        s++;
                        wsStack[s - 1] = x;
                    } else {
                        char y = wsStack[s - 1];
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(wsResult);
    }
}