import java.io.*;
import java.util.*;

public class PROB1 {
    private static int inputValue;
    private static int runningTotal;
    private static int[] userInput = new int[100001];
    private static int[] priorValue = new int[100001];
    private static int priorCount;
    private static int verboseFlag;
    private static int iterationCount;
    private static int priorIndex;

    public static void main(String[] args) {
        System.out.println("Welcome to the Advent of COBOL 2018 Edition!");
        System.out.println("--------------------------------------------");
        System.out.println();
        
        priorCount = 0;
        runningTotal = 0;
        verboseFlag = 1;
        iterationCount = 0;
        priorIndex = 1;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("PROB1.txt"));
            mainLoop(reader);
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(1);
        }
    }
    
    private static void mainLoop(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            priorIndex++;
            userInput[priorIndex] = Integer.parseInt(line.trim());
            inputValue = Integer.parseInt(line.trim());
            tallyHoHoHo();
        }
        finish();
    }
    
    private static void checkDuplicate() {
        if (priorCount > 100000) {
            System.out.println("ERROR: Count exceeds 100,000.");
            System.exit(0);
        }
        for (int i = 1; i <= priorCount; i++) {
            if (priorValue[i] == runningTotal) {
                System.out.println("Found duplicate                " + runningTotal);
                System.exit(0);
            }
        }
    }
    
    private static void tallyHoHoHo() {
        int printValue;
        String printMessage;
        
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
            System.out.println(printMessage + "                         " + printValue);
        }
        
        runningTotal += inputValue;
        checkDuplicate();
        priorCount++;
        priorIndex = priorCount;
        priorValue[priorIndex] = runningTotal;
    }
    
    private static void finish() {
        System.out.println("Resulting Frequency:          " + runningTotal);
    }
}