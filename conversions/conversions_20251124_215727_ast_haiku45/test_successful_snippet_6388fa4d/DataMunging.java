import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";
    
    private String dateStamp = "";
    private String inputDataPairs = "";
    private double val = 0.0;
    private int valLength = 0;
    private int flagLength = 0;
    private int offset = 0;
    private double dayTotal = 0.0;
    private double grandTotal = 0.0;
    private double meanVal = 0.0;
    private long dayRejected = 0;
    private long dayAccepted = 0;
    private long totalRejected = 0;
    private long totalAccepted = 0;
    private long currentDataGap = 0;
    private long maxDataGap = 0;
    private String maxDataGapEnd = "";
    
    public static void main(String[] args) {
        DataMunging program = new DataMunging();
        program.run();
    }
    
    public void run() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(INPUT_FILE_PATH));
            
            for (String line : lines) {
                if (line.isEmpty()) continue;
                
                String[] parts = line.split("\t");
                if (parts.length < 2) continue;
                
                dateStamp = parts[0];
                inputDataPairs = line.substring(dateStamp.length() + 1);
                
                processDataPairs();
                
                if (dayAccepted > 0) {
                    meanVal = dayTotal / dayAccepted;
                    System.out.println(dateStamp + " Reject: " + dayRejected + 
                                     " Accept: " + dayAccepted + 
                                     " Average: " + meanVal);
                }
                
                dayRejected = 0;
                dayAccepted = 0;
                meanVal = 0.0;
                dayTotal = 0.0;
            }
            
            System.out.println();
            System.out.println("File:         " + INPUT_FILE_PATH);
            System.out.println("Total:        " + grandTotal);
            System.out.println("Readings:     " + totalAccepted);
            
            if (totalAccepted > 0) {
                meanVal = grandTotal / totalAccepted;
                System.out.println("Average:      " + meanVal);
            }
            
            System.out.println();
            System.out.println("Bad readings: " + totalRejected);
            System.out.println("Maximum number of consecutive bad readings is " + maxDataGap);
            System.out.println("Ends on date " + maxDataGapEnd);
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading " + INPUT_FILE_PATH + ". " +
                             "File error: " + e.getMessage() + 
                             ". The program will terminate.");
        }
    }
    
    private void processDataPairs() {
        String remaining = inputDataPairs;
        
        while (!remaining.trim().isEmpty()) {
            int tabIndex = remaining.indexOf('\t');
            if (tabIndex == -1) {
                tabIndex = remaining.length();
            }
            
            String pair = remaining.substring(0, tabIndex).trim();
            if (pair.isEmpty()) {
                break;
            }
            
            String[] components = pair.split("\\s+");
            if (components.length < 2) {
                remaining = remaining.substring(tabIndex + 1);
                continue;
            }
            
            try {
                val = Double.parseDouble(components[0]);
                int flag = Integer.parseInt(components[1]);
                
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
            } catch (NumberFormatException e) {
                currentDataGap++;
                dayRejected++;
                totalRejected++;
            }
            
            if (tabIndex >= remaining.length()) {
                break;
            }
            remaining = remaining.substring(tabIndex + 1);
        }
    }
}