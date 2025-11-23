import java.io.*;
import java.util.*;

public class AOC2020132 {
    private static final String INPUT_FILE = "d13.input";
    
    public static void main(String[] args) {
        try {
            List<Integer> buses = new ArrayList<>();
            List<Integer> remainders = new ArrayList<>();
            
            // Read input file
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
            reader.readLine(); // Skip first line
            String line = reader.readLine(); // Read second line with bus IDs
            reader.close();
            
            // Parse bus IDs
            String[] parts = line.split(",");
            int j = 0;
            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].equals("x")) {
                    int busId = Integer.parseInt(parts[i]);
                    buses.add(busId);
                    
                    // Calculate remainder
                    int m = busId - i + 1;
                    int quotient = m / busId;
                    int remainder = m % busId;
                    if (remainder < 0) {
                        remainder += busId;
                    }
                    remainders.add(remainder);
                    j++;
                }
            }
            
            // Find timestamp
            long n = buses.get(0);
            long a = remainders.get(0);
            
            for (int i = 1; i < buses.size(); i++) {
                long n1 = buses.get(i);
                long a1 = remainders.get(i);
                
                long mod = 0;
                long quotient = 1;
                
                while (mod != a1) {
                    a += n;
                    quotient = a / n1;
                    mod = a % n1;
                }
                
                n *= n1;
            }
            
            System.out.println(n);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}