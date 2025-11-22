import java.util.Scanner;

public class PRMCHKRB {
    private static String numIn = "    ";
    private static int numInInt = 0;
    private static int wsQuotient = 0;
    private static int wsRemainder = 1;
    private static int wsDivisor = 0;

    public static void main(String[] args) {
        driverMain();
    }

    private static void driverMain() {
        System.out.println("Prime Number Checking Program");
        getUserInput();
        while (!userWantsToQuit()) {
            checkNumber();
        }
        System.out.println("EXIT");
    }

    private static void checkNumber() {
        divisionCalc();
        primeCheck();
        getUserInput();
    }

    private static void divisionCalc() {
        wsRemainder++;
        for (wsDivisor = 2; wsRemainder != 0 && wsDivisor != (numInInt - 1); wsDivisor++) {
            wsRemainder = numInInt % wsDivisor;
        }
    }

    private static void primeCheck() {
        if (wsRemainder == 0) {
            System.out.println(numIn + " IS NOT A PRIME");
        } else {
            System.out.println(numIn + " IS A PRIME");
        }
        numIn = "    ";
    }

    private static void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (!isNumeric(numIn) && !userWantsToQuit()) {
            System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
            System.out.println("(OR ENTER X TO QUIT).");
            numIn = scanner.nextLine();
            if (numIn.length() < 4) {
                numIn = String.format("%4s", numIn).replace(' ', '0');
            }
            if (numIn.length() > 4) {
                numIn = numIn.substring(0, 4);
            }
        }
        if (isNumeric(numIn)) {
            numInInt = Integer.parseInt(numIn.trim());
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean userWantsToQuit() {
        String trimmed = numIn.trim().toUpperCase();
        return trimmed.equals("X") || trimmed.equals("XX");
    }
}