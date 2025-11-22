import java.io.*;
import java.util.*;

public class Sqrt {
    static final int MAX_ITERATIONS = 1000;
    
    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(new File("sqrtFIXED.dat"));
            PrintWriter output = new PrintWriter(System.out);
            
            // Print title
            output.println("           SQUARE ROOT APPROXIMATIONS");
            output.println("--------------------------------------------");
            output.println("     NUMBER         SQUARE ROOT");
            output.println(" -------------------     ------------------");
            
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() < 17) continue;
                
                double z = Double.parseDouble(line.substring(0, 17).trim());
                double diff = Double.parseDouble(line.substring(17, 22).trim());
                
                if (z <= 0) {
                    output.printf("-%11.6f        INVALID INPUT%n", Math.abs(z));
                    continue;
                }
                
                double x = z / 2.0;
                double y = 0.0;
                boolean found = false;
                
                for (int k = 1; k <= MAX_ITERATIONS; k++) {
                    y = 0.5 * (x + z / x);
                    double temp = Math.abs(y - x);
                    double ratio = temp / (y + x);
                    
                    if (ratio > diff) {
                        x = y;
                    } else {
                        output.printf(" %11.6f     %11.6f%n", z, y);
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    output.printf(" %11.6f  ATTEMPT ABORTED,TOO MANY ITERATIONS%n", z);
                }
            }
            
            input.close();
            output.flush();
        } catch (Exception e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}