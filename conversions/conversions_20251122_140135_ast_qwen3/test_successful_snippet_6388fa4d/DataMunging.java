import java.io.*;
import java.util.*;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";
    
    // File status variables
    private static int fileStatus;
    private static boolean fileIsOk = false;
    private static boolean endOfFile = false;
    
    // Data pair variables
    private static double val;
    private static int flag;
    private static boolean invalidFlag = false;
    
    // Length variables
    private static int valLength;
    private static int flagLength;
    private static int offset;
    
    // Accumulator variables
    private static double dayTotal = 0.0;
    private static double grandTotal = 0.0;
    private static double meanVal = 0.0;
    
    // Counter variables
    private static int dayRejected = 0;
    private static int dayAccepted = 0;
    private static int totalRejected = 0;
    private static int totalAccepted = 0;
    
    // Gap variables
    private static int currentDataGap = 0;
    private static int maxDataGap = 0;
    private static String maxDataGapEnd = "";
    
    // Input file handling
    private static BufferedReader inputFile;
    
    public static void main(String[] args) {
        try {
            // Open the input file
            inputFile = new BufferedReader(new FileReader(INPUT_FILE_PATH));
            
            // Initialize file status
            fileStatus = 0;
            fileIsOk = true;
            endOfFile = false;
            
            if (!fileIsOk) {
                System.out.println("File could not be opened. The program will terminate.");
                return;
            }
            
            String line;
            while ((line = inputFile.readLine()) != null) {
                // Process each line
                String dateStamp = line.substring(0, 10);
                String inputDataPairs = line.substring(11).trim(); // Skip the tab character
                
                // Process the data pairs
                while (!inputDataPairs.isEmpty()) {
                    // Find the first tab delimiter
                    int tabIndex = inputDataPairs.indexOf('\t');
                    
                    if (tabIndex == -1) {
                        break; // No more data pairs
                    }
                    
                    // Extract the value and flag part
                    String valueAndFlag = inputDataPairs.substring(0, tabIndex);
                    inputDataPairs = inputDataPairs.substring(tabIndex + 1);
                    
                    // Split value and flag based on space
                    String[] parts = valueAndFlag.split("\\s+");
                    if (parts.length >= 2) {
                        try {
                            val = Double.parseDouble(parts[0]);
                            flag = Integer.parseInt(parts[1]);
                            
                            // Check if flag is invalid (-9 through 0)
                            invalidFlag = (flag >= -9 && flag <= 0);
                            
                            if (!invalidFlag) {
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
                            // Handle parsing errors gracefully
                            currentDataGap++;
                            dayRejected++;
                            totalRejected++;
                        }
                    } else {
                        // Handle incomplete data pair
                        currentDataGap++;
                        dayRejected++;
                        totalRejected++;
                    }
                }
                
                // Display day statistics
                if (dayAccepted > 0) {
                    meanVal = dayTotal / dayAccepted;
                    System.out.printf("%s Reject: %d Accept: %d Average: %.3f%n", 
                        dateStamp, dayRejected, dayAccepted, meanVal);
                } else {
                    System.out.printf("%s Reject: %d Accept: %d Average: 0.000%n", 
                        dateStamp, dayRejected, dayAccepted);
                }
                
                // Reset daily counters
                dayRejected = 0;
                dayAccepted = 0;
                meanVal = 0.0;
                dayTotal = 0.0;
            }
            
            // Close the input file
            inputFile.close();
            
            // Display overall statistics
            System.out.println();
            System.out.println("File:         " + INPUT_FILE_PATH);
            System.out.println("Total:        " + grandTotal);
            System.out.println("Readings:     " + totalAccepted);
            
            if (totalAccepted > 0) {
                meanVal = grandTotal / totalAccepted;
                System.out.println("Average:      " + meanVal);
            } else {
                System.out.println("Average:      0.000");
            }
            
            System.out.println();
            System.out.println("Bad readings: " + totalRejected);
            System.out.println("Maximum number of consecutive bad readings is " + maxDataGap);
            System.out.println("Ends on date " + maxDataGapEnd);
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading " + INPUT_FILE_PATH + 
                             ". File error: " + fileStatus + 
                             ". The program will terminate.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}