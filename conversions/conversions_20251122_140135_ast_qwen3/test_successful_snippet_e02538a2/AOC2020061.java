import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2020061 {
    private static final String INPUT_FILE = "d6.input";
    
    public static void main(String[] args) {
        int total = 0;
        int[] groupAnswers = new int[26];
        int groupTotal = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    total += groupTotal;
                    for (int i = 0; i < 26; i++) {
                        groupAnswers[i] = 0;
                    }
                    groupTotal = 0;
                } else {
                    for (int i = 0; i < line.length(); i++) {
                        char ch = line.charAt(i);
                        int index = (int) ch - 97;
                        if (groupAnswers[index] == 0) {
                            groupAnswers[index] = 1;
                            groupTotal++;
                        }
                    }
                }
            }
            
            // Process the last group
            total += groupTotal;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println(total);
    }
}