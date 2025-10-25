import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PROB1 {
    private static final int MAX_COUNT = 100000;
    private static int priorCount = 0;
    private static int runningTotal = 0;
    private static int moduloTotal = 0;
    private static int checkTotal = 0;
    private static String printMessage = new String(new char[30]);
    private static int printValue;
    private static int[] userInput = new int[MAX_COUNT];
    private static int[] priorValue = new int[MAX_COUNT];
    private static int priorIndex = 0;
    private static int verboseFlag = 1;
    private static int iterationCount = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Advent of COBOL 2018 Edition!");
        System.out.println("--------------------------------------------");
        System.out.println();

        try (BufferedReader br = new BufferedReader(new FileReader("PROB1.txt"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                priorIndex++;
                userInput[priorIndex - 1] = Integer.parseInt(inputRecord.trim());
                int inputValue = userInput[priorIndex - 1];

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
                    System.out.printf("%-30s%9d%n", printMessage, printValue);
                }

                runningTotal += inputValue;
                checkDuplicate();

                priorCount++;
                priorValue[priorCount - 1] = runningTotal;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printValue = runningTotal;
        printMessage = "Resulting Frequency:";
        System.out.printf("%-30s%9d%n", printMessage, printValue);
    }

    private static void checkDuplicate() {
        if (priorCount > MAX_COUNT) {
            System.out.println("ERROR: Count exceeds 100,000.");
            System.exit(1);
        }

        for (int i = 0; i < priorCount; i++) {
            if (priorValue[i] == runningTotal) {
                printMessage = "Found duplicate";
                printValue = runningTotal;
                System.out.printf("%-30s%9d%n", printMessage, printValue);
                System.exit(1);
            }
        }
    }
}