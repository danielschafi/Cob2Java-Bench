import java.io.*;
import java.util.*;

public class MaxLicensesInUse {
    private static String fileStatus = "00";
    private static int maxLicensesOut = 0;
    private static int numMaxTimes = 0;
    private static List<String> maxTimestamps = new ArrayList<>();
    private static int currentLicensesOut = 0;

    public static void main(String[] args) {
        try {
            openInputFile();
            
            if (!fileStatus.equals("00")) {
                System.out.println("File could not be opened. Error " + fileStatus + ".");
                System.exit(0);
            }

            readLicenseFile();
            
            System.out.println("License count at log end: " + currentLicensesOut);
            System.out.println("Maximum simulataneous licenses: " + maxLicensesOut);
            System.out.println("Time(s):");
            
            for (int i = 0; i < numMaxTimes; i++) {
                System.out.println(maxTimestamps.get(i));
            }
            
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred. Error " + fileStatus + ". The program will close.");
            System.exit(1);
        }
    }

    private static void openInputFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("mlijobs.txt"));
            reader.close();
            fileStatus = "00";
        } catch (FileNotFoundException e) {
            fileStatus = "35";
        } catch (IOException e) {
            fileStatus = "30";
        }
    }

    private static void readLicenseFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("mlijobs.txt"))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.length() < 30) {
                    continue;
                }
                
                String action = line.substring(8, 11).trim();
                String timestamp = line.substring(14, 33);
                
                if (action.equals("OUT")) {
                    currentLicensesOut++;
                    
                    if (currentLicensesOut > maxLicensesOut) {
                        numMaxTimes = 1;
                        maxLicensesOut = currentLicensesOut;
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
            
            fileStatus = "00";
        } catch (FileNotFoundException e) {
            fileStatus = "35";
            System.out.println("An unexpected error has occurred. Error " + fileStatus + ". The program will close.");
            System.exit(1);
        } catch (IOException e) {
            fileStatus = "30";
            System.out.println("An unexpected error has occurred. Error " + fileStatus + ". The program will close.");
            System.exit(1);
        }
    }
}