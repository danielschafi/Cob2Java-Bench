import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AOC2020132 {
    private static final String INPUT_FILE = "d13.input";
    
    public static void main(String[] args) {
        try {
            List<Integer> buses = new ArrayList<>();
            List<Integer> remainders = new ArrayList<>();
            
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
            reader.readLine(); // Skip first line
            
            String line = reader.readLine();
            reader.close();
            
            String[] parts = line.split(",");
            int j = 0;
            
            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].equals("x")) {
                    int busId = Integer.parseInt(parts[i]);
                    buses.add(busId);
                    int remainder = (busId - (i % busId)) % busId;
                    remainders.add(remainder);
                    j++;
                }
            }
            
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
            
            System.out.println(a);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}