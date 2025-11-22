import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DataMunging {
    private static final String INPUT_FILE_PATH = "readings.txt";
    
    private BigDecimal dayTotal = BigDecimal.ZERO;
    private BigDecimal grandTotal = BigDecimal.ZERO;
    private BigDecimal meanVal = BigDecimal.ZERO;
    
    private int dayRejected = 0;
    private int dayAccepted = 0;
    
    private int totalRejected = 0;
    private int totalAccepted = 0;
    
    private int currentDataGap = 0;
    private int maxDataGap = 0;
    private String maxDataGapEnd = "";
    
    public static void main(String[] args) {
        DataMunging program = new DataMunging();
        program.run();
    }
    
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_PATH))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                if (line.length() < 11) {
                    continue;
                }
                
                String dateStamp = line.substring(0, 10);
                String inputDataPairs = line.substring(11);
                
                String[] tokens = inputDataPairs.split("\t");
                
                for (int i = 0; i < tokens.length; i += 2) {
                    if (i + 1 >= tokens.length) {
                        break;
                    }
                    
                    String valStr = tokens[i].trim();
                    String flagStr = tokens[i + 1].trim();
                    
                    if (valStr.isEmpty() || flagStr.isEmpty()) {
                        continue;
                    }
                    
                    try {
                        BigDecimal val = new BigDecimal(valStr);
                        int flag = Integer.parseInt(flagStr);
                        
                        if (flag > 0) {
                            dayTotal = dayTotal.add(val);
                            grandTotal = grandTotal.add(val);
                            
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
                        // Skip invalid data
                    }
                }
                
                if (dayAccepted > 0) {
                    meanVal = dayTotal.divide(new BigDecimal(dayAccepted), 3, RoundingMode.HALF_UP);
                } else {
                    meanVal = BigDecimal.ZERO;
                }
                
                System.out.println(dateStamp + " Reject: " + dayRejected + 
                                   " Accept: " + dayAccepted + 
                                   " Average: " + meanVal);
                
                dayRejected = 0;
                dayAccepted = 0;
                meanVal = BigDecimal.ZERO;
                dayTotal = BigDecimal.ZERO;
            }
            
            System.out.println();
            System.out.println("File:         " + INPUT_FILE_PATH);
            System.out.println("Total:        " + grandTotal);
            System.out.println("Readings:     " + totalAccepted);
            
            if (totalAccepted > 0) {
                meanVal = grandTotal.divide(new BigDecimal(totalAccepted), 3, RoundingMode.HALF_UP);
            } else {
                meanVal = BigDecimal.ZERO;
            }
            System.out.println("Average:      " + meanVal);
            
            System.out.println();
            System.out.println("Bad readings: " + totalRejected);
            System.out.println("Maximum number of consecutive bad readings is " + maxDataGap);
            System.out.println("Ends on date " + maxDataGapEnd);
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.txt. " +
                               "File error: " + e.getMessage() + 
                               ". The program will terminate.");
            System.exit(1);
        }
    }
}