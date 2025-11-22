import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC202110 {
    public static void main(String[] args) {
        char[] wsStack = new char[100];
        int wsResult = 0;
        int s = 0;
        int wrong = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("d10.input"))) {
            String inputRecord;
            
            while ((inputRecord = br.readLine()) != null) {
                s = 0;
                wrong = 0;
                int recLen = inputRecord.length();
                
                for (int i = 0; i < recLen && wrong == 0; i++) {
                    char x = inputRecord.charAt(i);
                    
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