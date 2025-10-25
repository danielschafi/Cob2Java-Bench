import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";

    public static void main(String[] args) {
        BufferedReader reader = null;
        String line;
        int fileStatus = 0;
        double dayTotal = 0.0;
        double grandTotal = 0.0;
        double meanVal = 0.0;
        int dayRejected = 0;
        int dayAccepted = 0;
        int totalRejected = 0;
        int totalAccepted = 0;
        int currentDataGap = 0;
        int maxDataGap = 0;
        String maxDataGapEnd = "";

        try {
            reader = Files.newBufferedReader(Paths.get(INPUT_FILE_PATH), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("File could not be opened. The program will terminate.");
            return;
        }

        try {
            while ((line = reader.readLine()) != null) {
                String dateStamp = line.substring(0, 10);
                String inputDataPairs = line.substring(11);

                while (!inputDataPairs.trim().isEmpty()) {
                    String[] parts = inputDataPairs.split("\t", 2);
                    if (parts.length < 2) break;

                    double val = Double.parseDouble(parts[0]);
                    int flag = Integer.parseInt(parts[1]);

                    if (flag > 0) {
                        dayTotal += val;
                        grandTotal += val;
                        dayAccepted++;
                        totalAccepted++;

                        if (maxDataGap < currentDataGap) {
                            maxDataGap = currentDataGap;
                            maxDataGapEnd = dateStamp;
                        }
                        currentDataGap = 0;
                    } else {
                        currentDataGap++;
                        dayRejected++;
                        totalRejected++;
                    }

                    if (parts.length > 1) {
                        inputDataPairs = parts[1];
                    } else {
                        inputDataPairs = "";
                    }
                }

                meanVal = dayAccepted != 0 ? dayTotal / dayAccepted : 0.0;
                System.out.printf("%s Reject: %d Accept: %d Average: %.3f%n", dateStamp, dayRejected, dayAccepted, meanVal);

                dayTotal = 0.0;
                dayRejected = 0;
                dayAccepted = 0;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading " + INPUT_FILE_PATH + ". File error: " + fileStatus + ". The program will terminate.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println();
        System.out.println("File:         " + INPUT_FILE_PATH);
        System.out.println("Total:        " + grandTotal);
        System.out.println("Readings:     " + totalAccepted);

        meanVal = totalAccepted != 0 ? grandTotal / totalAccepted : 0.0;
        System.out.printf("Average:      %.3f%n", meanVal);

        System.out.println();
        System.out.println("Bad readings: " + totalRejected);
        System.out.println("Maximum number of consecutive bad readings is " + maxDataGap);
        System.out.println("Ends on date " + maxDataGapEnd);
    }
}