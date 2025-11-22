import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";
    
    private static class DataPair {
        double val;
        int flag;
    }
    
    public static void main(String[] args) {
        try {
            processFile();
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.txt. " +
                    "The program will terminate.");
            System.exit(1);
        }
    }
    
    private static void processFile() throws IOException {
        Path filePath = Paths.get(INPUT_FILE_PATH);
        
        if (!Files.exists(filePath)) {
            System.out.println("File could not be opened. The program will terminate.");
            System.exit(1);
        }
        
        double dayTotal = 0;
        double grandTotal = 0;
        double meanVal = 0;
        
        int dayRejected = 0;
        int dayAccepted = 0;
        
        int totalRejected = 0;
        int totalAccepted = 0;
        
        int currentDataGap = 0;
        int maxDataGap = 0;
        String maxDataGapEnd = "";
        
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String dateStamp = "";
                String inputDataPairs = "";
                
                int tabIndex = line.indexOf('\t');
                if (tabIndex > 0) {
                    dateStamp = line.substring(0, tabIndex);
                    inputDataPairs = line.substring(tabIndex + 1);
                } else {
                    continue;
                }
                
                while (!inputDataPairs.trim().isEmpty()) {
                    int nextTab = inputDataPairs.indexOf('\t');
                    String valStr;
                    String flagStr;
                    
                    if (nextTab > 0) {
                        valStr = inputDataPairs.substring(0, nextTab);
                        inputDataPairs = inputDataPairs.substring(nextTab + 1);
                        
                        int nextTab2 = inputDataPairs.indexOf('\t');
                        if (nextTab2 > 0) {
                            flagStr = inputDataPairs.substring(0, nextTab2);
                            inputDataPairs = inputDataPairs.substring(nextTab2 + 1);
                        } else {
                            flagStr = inputDataPairs.trim();
                            inputDataPairs = "";
                        }
                    } else {
                        break;
                    }
                    
                    try {
                        double val = Double.parseDouble(valStr);
                        int flag = Integer.parseInt(flagStr);
                        
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
                        break;
                    }
                }
                
                if (dayAccepted > 0) {
                    meanVal = dayTotal / dayAccepted;
                    System.out.println(dateStamp + " Reject: " + dayRejected +
                            " Accept: " + dayAccepted + " Average: " + meanVal);
                }
                
                dayRejected = 0;
                dayAccepted = 0;
                meanVal = 0;
                dayTotal = 0;
            }
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
    }
}