import java.io.*;
import java.util.*;

public class Sqrt {
    private static final String INPUT_FILE = "sqrtFIXED.dat";
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
             PrintWriter writer = new PrintWriter(System.out)) {
            
            // Print title
            writer.println("         SQUARE ROOT APPROXIMATIONS");
            writer.println("--------------------------------------------");
            writer.println("       NUMBER         SQUARE ROOT");
            writer.println(" -------------------     ------------------");
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() < 12) continue;
                
                double inZ = Double.parseDouble(line.substring(0, 11).trim());
                double diff = Double.parseDouble(line.substring(11, 16).trim());
                
                if (inZ <= 0) {
                    writer.printf(" %11.6f        INVALID INPUT%n", inZ);
                    continue;
                }
                
                double z = inZ;
                double x = z / 2.0;
                int k = 0;
                double y = 0;
                double temp = 0;
                
                boolean converged = false;
                while (k < 1000) {
                    k++;
                    y = 0.5 * (x + z / x);
                    
                    temp = Math.abs(y - x);
                    if (temp / (y + x) > diff) {
                        x = y;
                        continue;
                    }
                    
                    // Print result
                    writer.printf(" %11.6f     %11.6f%n", z, y);
                    converged = true;
                    break;
                }
                
                if (!converged) {
                    writer.printf(" %11.6f  ATTEMPT ABORTED,TOO MANY ITERATIONS%n", z);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
}