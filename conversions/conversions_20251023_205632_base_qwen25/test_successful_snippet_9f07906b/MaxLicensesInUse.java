import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MaxLicensesInUse {
    public static void main(String[] args) {
        String fileName = "mlijobs.txt";
        String fileStatus = "";
        int maxLicensesOut = 0;
        int numMaxTimes = 0;
        List<String> maxTimestamps = new ArrayList<>(50);
        int currentLicensesOut = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 14 && line.substring(8, 11).equals("OUT")) {
                    currentLicensesOut++;

                    if (currentLicensesOut > maxLicensesOut) {
                        numMaxTimes = 1;
                        maxLicensesOut = currentLicensesOut;
                        maxTimestamps.clear();
                        maxTimestamps.add(line.substring(14, 33));
                    } else if (currentLicensesOut == maxLicensesOut) {
                        numMaxTimes++;
                        maxTimestamps.add(line.substring(14, 33));
                    }
                } else {
                    currentLicensesOut--;
                }
            }
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred. The program will close.");
            return;
        }

        System.out.println("License count at log end: " + currentLicensesOut);
        System.out.println("Maximum simultaneous licenses: " + maxLicensesOut);
        System.out.println("Time(s):");
        for (int i = 0; i < numMaxTimes; i++) {
            System.out.println(maxTimestamps.get(i));
        }
    }
}