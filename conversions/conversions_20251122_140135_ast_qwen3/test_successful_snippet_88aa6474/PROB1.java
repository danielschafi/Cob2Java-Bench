import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PROB1 {
    private static final int MAX_ENTRIES = 100000;
    
    private static int[] priorValue = new int[MAX_ENTRIES];
    private static int priorCount = 0;
    private static int runningTotal = 0;
    private static int verboseFlag = 1;
    private static String printMessage = "";
    private static int printValue = 0;
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Advent of COBOL 2018 Edition!");
        System.out.println("--------------------------------------------");
        System.out.println();
        
        try (BufferedReader br = new BufferedReader(new FileReader("PROB1.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() >= 10) {
                    String inputRecord = line.substring(0, 10).trim();
                    try {
                        int inputValue = Integer.parseInt(inputRecord);
                        performTallyHoHoHo(inputValue);
                    } catch (NumberFormatException e) {
                        // Skip invalid lines
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
        
        printValue = runningTotal;
        printMessage = "Resulting Frequency:";
        System.out.println(printMessage + " " + printValue);
    }
    
    private static void performTallyHoHoHo(int inputValue) {
        if (inputValue < 0) {
            printValue = -inputValue;
        } else {
            printValue = inputValue;
        }
        
        if (inputValue > 0) {
            printMessage = "Nice!";
        } else {
            printMessage = "Naughty!";
        }
        
        if (verboseFlag == 1) {
            System.out.println(printMessage + " " + printValue);
        }
        
        runningTotal += inputValue;
        checkDuplicate();
        priorCount++;
        if (priorCount < MAX_ENTRIES) {
            priorValue[priorCount - 1] = runningTotal;
        }
    }
    
    private static void checkDuplicate() {
        if (priorCount >= MAX_ENTRIES) {
            System.err.println("ERROR: Count exceeds 100,000.");
            System.exit(1);
        }
        
        for (int i = 0; i < priorCount; i++) {
            if (priorValue[i] == runningTotal) {
                printMessage = "Found duplicate";
                printValue = runningTotal;
                System.out.println(printMessage + " " + printValue);
                System.exit(0);
            }
        }
    }
}