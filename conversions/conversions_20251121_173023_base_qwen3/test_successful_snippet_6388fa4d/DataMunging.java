import java.io.*;
import java.util.*;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";
    
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_PATH))) {
            String line;
            int totalRejected = 0;
            int totalAccepted = 0;
            double grandTotal = 0.0;
            int maxDataGap = 0;
            String maxDataGapEnd = "";
            String currentDate = "";
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\t");
                if (parts.length < 2) continue;
                
                currentDate = parts[0];
                int dayRejected = 0;
                int dayAccepted = 0;
                double dayTotal = 0.0;
                int currentDataGap = 0;
                
                for (int i = 1; i < parts.length; i++) {
                    String dataPair = parts[i];
                    if (dataPair.isEmpty()) continue;
                    
                    String[] pairParts = dataPair.split(",");
                    if (pairParts.length != 2) continue;
                    
                    try {
                        double value = Double.parseDouble(pairParts[0]);
                        int flag = Integer.parseInt(pairParts[1]);
                        
                        if (flag >= 0) {
                            dayTotal += value;
                            grandTotal += value;
                            dayAccepted++;
                            totalAccepted++;
                            
                            if (currentDataGap > maxDataGap) {
                                maxDataGap = currentDataGap;
                                maxDataGapEnd = currentDate;
                            }
                            
                            currentDataGap = 0;
                        } else {
                            currentDataGap++;
                            dayRejected++;
                            totalRejected++;
                        }
                    } catch (NumberFormatException e) {
                        // Skip invalid data
                    }
                }
                
                if (dayAccepted > 0) {
                    double meanVal = dayTotal / dayAccepted;
                    System.out.printf("%s Reject: %d Accept: %d Average: %.3f%n", 
                                    currentDate, dayRejected, dayAccepted, meanVal);
                } else {
                    System.out.printf("%s Reject: %d Accept: %d Average: %.3f%n", 
                                    currentDate, dayRejected, dayAccepted, 0.0);
                }
            }
            
            System.out.println();
            System.out.println("File:         " + INPUT_FILE_PATH);
            System.out.println("Total:        " + String.format("%.3f", grandTotal));
            System.out.println("Readings:     " + totalAccepted);
            
            if (totalAccepted > 0) {
                double meanVal = grandTotal / totalAccepted;
                System.out.println("Average:      " + String.format("%.3f", meanVal));
            } else {
                System.out.println("Average:      0.000");
            }
            
            System.out.println();
            System.out.println("Bad readings: " + totalRejected);
            System.out.println("Maximum number of consecutive bad readings is " + maxDataGap);
            System.out.println("Ends on date " + maxDataGapEnd);
            
        } catch (IOException e) {
            System.err.println("An error occurred while reading " + INPUT_FILE_PATH + 
                             ". File error: " + e.getMessage() + 
                             ". The program will terminate.");
            System.exit(1);
        }
    }
}