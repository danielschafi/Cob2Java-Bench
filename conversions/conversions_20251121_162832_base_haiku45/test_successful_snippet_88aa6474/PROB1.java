import java.io.*;
import java.util.*;

public class PROB1 {
    static class DataEntry {
        int userInput;
        int priorValue;
    }

    static class PrintLine {
        String printMessage;
        int printValue;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Advent of COBOL 2018 Edition!");
        System.out.println("--------------------------------------------");
        System.out.println();

        int priorCount = 0;
        int runningTotal = 0;
        int verboseFlag = 1;
        int iterationCount = 0;
        int priorIndex = 1;

        DataEntry[] dataTable = new DataEntry[100000];
        for (int i = 0; i < dataTable.length; i++) {
            dataTable[i] = new DataEntry();
        }

        PrintLine printLine = new PrintLine();

        try (BufferedReader reader = new BufferedReader(new FileReader("PROB1.txt"))) {
            String inputRecord;

            while ((inputRecord = reader.readLine()) != null) {
                priorIndex++;
                int inputValue = Integer.parseInt(inputRecord.trim());
                dataTable[priorIndex - 1].userInput = inputValue;

                // TALLY-HO-HO-HO
                int printValue;
                if (inputValue < 0) {
                    printValue = -inputValue;
                } else {
                    printValue = inputValue;
                }

                if (inputValue > 0) {
                    printLine.printMessage = "Nice!";
                } else {
                    printLine.printMessage = "Naughty!";
                }

                if (verboseFlag == 1) {
                    System.out.println(printLine.printMessage + " " + printValue);
                }

                runningTotal += inputValue;

                // CHECK-DUPLICATE
                if (priorCount > 100000) {
                    System.out.println("ERROR: Count exceeds 100,000.");
                    System.exit(1);
                }

                boolean foundDuplicate = false;
                for (int i = 1; i <= priorCount; i++) {
                    if (dataTable[i - 1].priorValue == runningTotal) {
                        System.out.println("Found duplicate " + runningTotal);
                        System.exit(0);
                    }
                }

                priorCount++;
                priorIndex = priorCount;
                dataTable[priorIndex - 1].priorValue = runningTotal;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        printLine.printValue = runningTotal;
        printLine.printMessage = "Resulting Frequency:";
        System.out.println(printLine.printMessage + " " + printLine.printValue);
    }
}