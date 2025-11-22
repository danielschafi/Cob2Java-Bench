import java.io.*;
import java.util.*;

public class AOC202013Part2 {
    private static final String INPUT_FILE = "d13.input";
    private static final int MAX_BUSES = 99;
    
    private int[] wsBuses = new int[MAX_BUSES];
    private int[] wsRemainders = new int[MAX_BUSES];
    private int len = 0;
    
    public static void main(String[] args) {
        AOC202013Part2 program = new AOC202013Part2();
        program.run();
    }
    
    private void run() {
        try {
            read();
            long result = findTimestamp();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
        
        String line1 = reader.readLine();
        String line2 = reader.readLine();
        
        reader.close();
        
        String[] parts = line2.split(",");
        int j = 0;
        
        for (int i = 0; i < parts.length; i++) {
            String buffer = parts[i].trim();
            
            if (!buffer.equals("x")) {
                int busId = Integer.parseInt(buffer);
                wsBuses[j] = busId;
                
                int remainder = busId - (i % busId);
                if (remainder < 0) {
                    remainder += busId;
                }
                wsRemainders[j] = remainder;
                j++;
            }
        }
        
        len = j;
    }
    
    private long findTimestamp() {
        long n = wsBuses[0];
        long a = wsRemainders[0];
        
        for (int i = 1; i < len; i++) {
            long n1 = wsBuses[i];
            long a1 = wsRemainders[i];
            
            long mod = 0;
            
            while (mod != a1) {
                a = a + n;
                mod = a % n1;
            }
            
            n = n * n1;
        }
        
        return a;
    }
}