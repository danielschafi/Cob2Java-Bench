import java.io.*;
import java.util.*;

public class MaxLicensesInUse {
    private static final String LICENSE_FILE_PATH = "mlijobs.txt";
    
    // File status variables
    private static String fileStatus = "";
    private static boolean fileOk = false;
    
    // Working storage variables
    private static int maxLicensesOut = 0;
    private static int numMaxTimes = 0;
    private static String[] maxTimestamps = new String[50];
    private static int currentLicensesOut = 0;
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(LICENSE_FILE_PATH));
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 12) {
                    String action = line.substring(8, 11);
                    String timestamp = line.substring(12, 31);
                    
                    if ("OUT".equals(action)) {
                        currentLicensesOut++;
                        
                        if (currentLicensesOut > maxLicensesOut) {
                            numMaxTimes = 1;
                            maxLicensesOut = currentLicensesOut;
                            maxTimestamps[numMaxTimes - 1] = timestamp;
                        } else if (currentLicensesOut == maxLicensesOut) {
                            numMaxTimes++;
                            maxTimestamps[numMaxTimes - 1] = timestamp;
                        }
                    } else {
                        currentLicensesOut--;
                    }
                }
            }
            
            reader.close();
            
            System.out.println("License count at log end: " + currentLicensesOut);
            System.out.println("Maximum simultaneous licenses: " + maxLicensesOut);
            System.out.println("Time(s):");
            
            for (int i = 0; i < numMaxTimes; i++) {
                System.out.println(maxTimestamps[i]);
            }
            
        } catch (IOException e) {
            System.err.println("An unexpected error has occurred. Error " + fileStatus + ". The program will close.");
            System.exit(1);
        }
    }
}