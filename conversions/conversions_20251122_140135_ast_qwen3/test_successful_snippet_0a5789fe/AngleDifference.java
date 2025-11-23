import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

public class AngleDifference {
    private static final String INPUT_FILE = "Angle_diff.txt";
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE))) {
            String line;
            int recordCounter = 0;
            
            while ((line = reader.readLine()) != null) {
                recordCounter++;
                String[] parts = line.trim().split("\\s+");
                
                if (parts.length >= 2) {
                    String bearing1Str = parts[0];
                    String bearing2Str = parts[1];
                    
                    double bearing1 = parseAngle(bearing1Str);
                    double bearing2 = parseAngle(bearing2Str);
                    
                    double result = bearing2 - bearing1;
                    
                    // Normalize result to [-180, 180) range
                    if (result > 180) {
                        result -= 360;
                    } else if (result <= -180) {
                        result += 360;
                    }
                    
                    DecimalFormat df = new DecimalFormat("0.0000");
                    System.out.println(String.format("%03d %s", recordCounter, df.format(result)));
                }
            }
            
            System.out.println("RECORDS PROCESSED: " + recordCounter);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static double parseAngle(String angleStr) {
        // Remove leading '+' if present
        if (angleStr.startsWith("+")) {
            angleStr = angleStr.substring(1);
        }
        
        // Parse the angle string
        return Double.parseDouble(angleStr);
    }
}