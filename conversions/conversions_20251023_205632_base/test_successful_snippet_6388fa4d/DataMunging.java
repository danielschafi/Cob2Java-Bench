import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";

    public static void main(String[] args) {
        String fileStatus = "00";
        String dateStamp;
        String inputDataPairs;
        double val;
        int flag;
        int valLength;
        int flagLength;
        int offset;
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

        try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                dateStamp = line.substring(0, 10);
                inputDataPairs = line.substring(12);

                while (!inputDataPairs.trim().isEmpty()) {
                    String[] parts = inputDataPairs.split("\t", 2);
                    val = Double.parseDouble(parts[0].trim());
                    flag = Integer.parseInt(parts[0].trim().substring(parts[0].trim().length() - 1));
                    valLength = parts[0].trim().length() - 1;
                    flagLength = 1;

                    offset = valLength + flagLength + 1;
                    inputDataPairs = parts.length > 1 ? parts[1] : "";

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
                }

                if (dayAccepted > 0) {
                    meanVal = dayTotal / dayAccepted;
                } else {
                    meanVal = 0.0;
                }

                System.out.printf("%s Reject: %d Accept: %d Average: %.3f%n", dateStamp, dayRejected, dayAccepted, meanVal);

                dayTotal = 0.0;
                dayRejected = 0;
                dayAccepted = 0;
                meanVal = 0.0;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading " + INPUT_FILE_PATH + ". File error: " + fileStatus + ". The program will terminate.");
            return;
        }

        if (totalAccepted > 0) {
            meanVal = grandTotal / totalAccepted;
        } else {
            meanVal = 0.0;
        }

        System.out.println();
        System.out.println("File:         " + INPUT_FILE_PATH);
        System.out.println("Total:        " + grandTotal);
        System.out.println("Readings:     " + totalAccepted);
        System.out.println("Average:      " + String.format("%.3f", meanVal));
        System.out.println();
        System.out.println("Bad readings: " + totalRejected);
        System.out.println("Maximum number of consecutive bad readings is " + maxDataGap);
        System.out.println("Ends on date " + maxDataGapEnd);
    }
}