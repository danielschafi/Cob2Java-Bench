import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PROB1 {
    private static final int MAX_ENTRIES = 100000;
    private static int[] userInput = new int[MAX_ENTRIES];
    private static int[] priorValue = new int[MAX_ENTRIES];
    private static int priorCount = 0;
    private static int runningTotal = 0;
    private static int verboseFlag = 1;
    private static String printMessage;
    private static int printValue;

    public static void main(String[] args) {
        System.out.println("Welcome to the Advent of COBOL 2018 Edition!");
        System.out.println("--------------------------------------------");
        System.out.println();

        try (BufferedReader br = new BufferedReader(new FileReader("PROB1.txt"))) {
            String inputRecord;
            while ((inputRecord = br.readLine()) != null) {
                priorCount++;
                if (priorCount > MAX_ENTRIES) {
                    System.out.println("ERROR: Count exceeds 100,000.");
                    return;
                }
                userInput[priorCount - 1] = Integer.parseInt(inputRecord);
                runningTotal += userInput[priorCount - 1];

                if (userInput[priorCount - 1] < 0) {
                    printValue = -userInput[priorCount - 1];
                } else {
                    printValue = userInput[priorCount - 1];
                }

                if (userInput[priorCount - 1] > 0) {
                    printMessage = "Nice!";
                } else {
                    printMessage = "Naughty!";
                }

                if (verboseFlag == 1) {
                    System.out.println(printMessage + " " + printValue);
                }

                priorValue[priorCount - 1] = runningTotal;

                for (int i = 0; i < priorCount - 1; i++) {
                    if (priorValue[i] == runningTotal) {
                        System.out.println("Found duplicate " + runningTotal);
                        return;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMessage = "Resulting Frequency:";
        printValue = runningTotal;
        System.out.println(printMessage + " " + printValue);
    }
}