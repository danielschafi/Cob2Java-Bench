```java
import java.io.*;
import java.util.*;

public class MaxLicensesInUse {
    private static String fileStatus = "00";
    private static int maxLicensesOut = 0;
    private static int numMaxTimes = 0;
    private static String[] maxTimestamps = new String[50];
    private static int currentLicensesOut = 0;
    private static int i = 0;

    public static void main(String[] args) {
        try {
            openInputFile();
            if (!fileStatus.equals("00")) {
                System.out.println("File could not be opened. Error " + fileStatus + ".");
                System.exit(1);
            }

            processFile();
            closeInputFile();

            System.out.println("License count at log end: " + currentLicensesOut);
            System.out.println("Maximum simulataneous licenses: " + maxLicensesOut);
            System.out.println("Time(s):");
            for (i = 1; i <= numMaxTimes; i++) {
                System.out.println(maxTimestamps[i - 1]);
            }
        } catch (Exception e) {
            System.out.println("An unexpected error has occurred. Error " + fileStatus + ". The program will close.");
            System.exit(1);
        }
    }

    private static void openInputFile() {
        try {
            fileStatus = "00";
        } catch (Exception e) {
            fileStatus = "99";
        }
    }

    private static void closeInputFile() {
        try {
            fileStatus = "00";
        } catch (Exception e) {
            fileStatus = "99";
        }
    }

    private static void processFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("mlijobs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 11) {
                    String action = line.substring(8, 11).trim();
                    String timestamp = line.substring(14, 33);

                    if (action.equals("OUT")) {
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
            fileStatus = "00";
        } catch (IOException e) {
            fileStatus = "99";
        }
    }
}