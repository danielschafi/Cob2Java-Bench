import java.io.*;
import java.nio.file.*;

public class AOC202110 {
    public static void main(String[] args) {
        int fileStatus = 0;
        int wsResult = 0;
        
        try {
            String content = new String(Files.readAllBytes(Paths.get("d10.input")));
            String[] lines = content.split("\n");
            
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                
                char[] wsStack = new char[100];
                int s = 0;
                int wrong = 0;
                int recLen = line.length();
                
                for (int i = 0; i < recLen && wrong == 0; i++) {
                    char x = line.charAt(i);
                    
                    if (x == '(' || x == '[' || x == '{' || x == '<') {
                        wsStack[s] = x;
                        s++;
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
            
            System.out.println(wsResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}