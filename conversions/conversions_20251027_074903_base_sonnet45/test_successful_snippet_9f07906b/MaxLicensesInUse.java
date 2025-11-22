import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MaxLicensesInUse {
    private static class LicenseRecord {
        String action;
        String licenseTimestamp;
        
        public LicenseRecord(String line) {
            if (line.length() >= 33) {
                this.action = line.substring(8, 11);
                this.licenseTimestamp = line.substring(14, 33);
            } else {
                this.action = "";
                this.licenseTimestamp = "";
            }
        }
        
        public boolean isLicenseOut() {
            return "OUT".equals(action);
        }
    }
    
    public static void main(String[] args) {
        String fileName = "mlijobs.txt";
        int maxLicensesOut = 0;
        int numMaxTimes = 0;
        List<String> maxTimestamps = new ArrayList<>();
        int currentLicensesOut = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                LicenseRecord record = new LicenseRecord(line);
                
                if (record.isLicenseOut()) {
                    currentLicensesOut++;
                    
                    if (currentLicensesOut > maxLicensesOut) {
                        numMaxTimes = 1;
                        maxLicensesOut = currentLicensesOut;
                        maxTimestamps.clear();
                        maxTimestamps.add(record.licenseTimestamp);
                    } else if (currentLicensesOut == maxLicensesOut) {
                        numMaxTimes++;
                        maxTimestamps.add(record.licenseTimestamp);
                    }
                } else {
                    currentLicensesOut--;
                }
            }
            
            System.out.println("License count at log end: " + currentLicensesOut);
            System.out.println("Maximum simulataneous licenses: " + maxLicensesOut);
            System.out.println("Time(s):");
            for (String timestamp : maxTimestamps) {
                System.out.println(timestamp);
            }
            
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred. Error " + e.getMessage() + ". The program will close.");
            System.exit(1);
        }
    }
}