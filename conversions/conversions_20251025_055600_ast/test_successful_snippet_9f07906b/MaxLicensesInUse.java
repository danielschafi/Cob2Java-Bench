import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaxLicensesInUse {
    private static final int MAX_TIMESTAMPS = 50;
    private static String[] maxTimestamps = new String[MAX_TIMESTAMPS];
    private static int fileStatus;
    private static int maxLicensesOut;
    private static int numMaxTimes;
    private static int currentLicensesOut;
    private static int i;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("mlijobs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() >= 14 && "OUT".equals(line.substring(8, 11))) {
                    currentLicensesOut++;
                    if (currentLicensesOut > maxLicensesOut) {
                        numMaxTimes = 1;
                        maxLicensesOut = currentLicensesOut;
                        maxTimestamps[numMaxTimes - 1] = line.substring(12, 31);
                    } else if (currentLicensesOut == maxLicensesOut) {
                        numMaxTimes++;
                        maxTimestamps[numMaxTimes - 1] = line.substring(12, 31);
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
        for (i = 0; i < numMaxTimes; i++) {
            System.out.println(maxTimestamps[i]);
        }
    }
}