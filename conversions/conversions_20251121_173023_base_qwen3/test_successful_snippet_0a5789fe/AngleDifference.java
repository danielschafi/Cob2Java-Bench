import java.io.*;
import java.util.*;

public class AngleDifference {
    private static final String INPUT_FILE = "Angle_diff.txt";
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int recordCount = 0;
            
            while ((line = reader.readLine()) != null) {
                recordCount++;
                String[] parts = line.trim().split("\\s+");
                
                if (parts.length >= 2) {
                    String bearing1Str = parts[0];
                    String bearing2Str = parts[1];
                    
                    double bearing1 = parseAngle(bearing1Str);
                    double bearing2 = parseAngle(bearing2Str);
                    
                    double result = bearing2 - bearing1;
                    
                    // Normalize to [-180, 180]
                    if (result > 180) {
                        result -= 360;
                    } else if (result < -180) {
                        result += 360;
                    }
                    
                    System.out.printf("%03d %10.4f%n", recordCount, result);
                }
            }
            
            System.out.println("RECORDS PROCESSED: " + recordCount);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    private static double parseAngle(String angleStr) {
        // Remove leading zeros and handle sign
        boolean negative = angleStr.startsWith("-");
        String cleanStr = angleStr.replaceFirst("^-", "").replaceFirst("^0+", "");
        
        if (cleanStr.isEmpty()) {
            return 0.0;
        }
        
        // Find decimal point position
        int decimalPos = cleanStr.indexOf('.');
        double value;
        
        if (decimalPos == -1) {
            // No decimal point
            value = Double.parseDouble(cleanStr);
        } else {
            // Has decimal point
            value = Double.parseDouble(cleanStr);
        }
        
        return negative ? -value : value;
    }
}