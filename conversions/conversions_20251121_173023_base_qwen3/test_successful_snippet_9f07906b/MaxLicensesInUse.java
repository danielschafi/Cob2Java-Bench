import java.io.*;
import java.util.*;

public class MaxLicensesInUse {
    private static final String LICENSE_FILE = "mlijobs.txt";
    
    private static int maxLicensesOut = 0;
    private static int numMaxTimes = 0;
    private static List<String> maxTimestamps = new ArrayList<>();
    private static int currentLicensesOut = 0;
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(LICENSE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 12) {
                    String action = line.substring(8, 11);
                    String timestamp = line.substring(12, 31);
                    
                    if ("OUT".equals(action)) {
                        currentLicensesOut++;
                        
                        if (currentLicensesOut > maxLicensesOut) {
                            maxLicensesOut = currentLicensesOut;
                            numMaxTimes = 1;
                            maxTimestamps.clear();
                            maxTimestamps.add(timestamp);
                        } else if (currentLicensesOut == maxLicensesOut) {
                            numMaxTimes++;
                            maxTimestamps.add(timestamp);
                        }
                    } else {
                        currentLicensesOut--;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An unexpected error has occurred. Error: " + e.getMessage());
            return;
        }
        
        System.out.println("License count at log end: " + currentLicensesOut);
        System.out.println("Maximum simultaneous licenses: " + maxLicensesOut);
        System.out.println("Time(s):");
        for (String timestamp : maxTimestamps) {
            System.out.println(timestamp);
        }
    }
}