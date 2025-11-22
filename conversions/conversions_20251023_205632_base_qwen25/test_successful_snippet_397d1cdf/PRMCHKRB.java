import java.util.Scanner;

public class PRMCHKRB {
    public static void main(String[] args) {
        String numIn = "";
        int numInInt = 0;
        int wsQuotient = 0;
        int wsRemainder = 1;
        int wsDivisor = 0;
        boolean notPrimeNumber = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Prime Number Checking Program");

        while (true) {
            getUserInput(numIn, scanner);
            if (numIn.equalsIgnoreCase("x")) {
                break;
            }
            numInInt = Integer.parseInt(numIn);
            divisionCalc(numInInt, wsRemainder, wsDivisor);
            notPrimeNumber = (wsRemainder == 0);
            primeChk(numIn, notPrimeNumber);
        }
        System.out.println("EXIT");
        scanner.close();
    }

    private static void divisionCalc(int numInInt, int wsRemainder, int wsDivisor) {
        wsRemainder = 1;
        for (wsDivisor = 2; wsRemainder != 0 && wsDivisor < numInInt - 1; wsDivisor++) {
            wsRemainder = numInInt % wsDivisor;
        }
    }

    private static void primeChk(String numIn, boolean notPrimeNumber) {
        if (notPrimeNumber) {
            System.out.println(numIn + " IS NOT A PRIME");
        } else {
            System.out.println(numIn + " IS A PRIME");
        }
    }

    private static void getUserInput(String numIn, Scanner scanner) {
        do {
            System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
            System.out.println("(OR ENTER X TO QUIT).");
            numIn = scanner.nextLine();
        } while (!numIn.matches("\\d{4}|x|X"));
    }
}