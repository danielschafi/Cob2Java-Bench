import java.io.*;
import java.util.*;

public class PROB1 {
    private static long inputValue = 0;
    private static long runningTotal = 0;
    private static long moduloTotal = 0;
    private static long checkTotal = 0;
    private static String printMessage = "";
    private static long printValue = 0;
    private static long[] userInput = new long[100000];
    private static long[] priorValue = new long[100000];
    private static long priorCount = 0;
    private static int verboseFlag = 0;
    private static long iterationCount = 0;
    private static int priorIndex = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Advent of COBOL 2018 Edition!");
        System.out.println("--------------------------------------------");
        System.out.println();

        priorCount = 0;
        runningTotal = 0;
        verboseFlag = 1;
        iterationCount = 0;
        priorIndex = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader("PROB1.txt"))) {
            mainLoop(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mainLoop(BufferedReader reader) throws IOException {
        String inputRecord;
        while ((inputRecord = reader.readLine()) != null) {
            priorIndex++;
            userInput[(int)priorIndex - 1] = Long.parseLong(inputRecord.trim());
            inputValue = Long.parseLong(inputRecord.trim());
            tallyHoHoHo();
        }
        finish();
    }

    private static void tallyHoHoHo() {
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
        priorIndex = (int)priorCount;
        priorValue[(int)priorIndex - 1] = runningTotal;
    }

    private static void checkDuplicate() {
        if (priorCount > 100000) {
            System.out.println("ERROR: Count exceeds 100,000.");
            System.exit(1);
        }

        for (int i = 1; i <= priorCount; i++) {
            if (priorValue[i - 1] == runningTotal) {
                printMessage = "Found duplicate";
                printValue = runningTotal;
                System.out.println(printMessage + " " + printValue);
                System.exit(0);
            }
        }
    }

    private static void finish() {
        printValue = runningTotal;
        printMessage = "Resulting Frequency:";
        System.out.println(printMessage + " " + printValue);
    }
}