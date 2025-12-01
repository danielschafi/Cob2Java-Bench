import java.util.Scanner;

public class PRMCHKRB {
    private static String numIn = "";
    private static int numInInt = 0;
    private static int wsQuotient = 0;
    private static int wsRemainder = 1;
    private static int wsDivisor = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        driver();
    }

    private static void driver() {
        System.out.println("Prime Number Checking Program");
        getInput();
        while (!userWantsToQuit()) {
            checkNumber();
        }
        System.out.println("EXIT");
    }

    private static void checkNumber() {
        divisionCalc();
        primeCheck();
        getInput();
    }

    private static void divisionCalc() {
        wsRemainder++;
        wsDivisor = 2;
        while (wsRemainder != 0 && wsDivisor < (numInInt - 1)) {
            wsRemainder = numInInt % wsDivisor;
            wsDivisor++;
        }
    }

    private static void primeCheck() {
        if (wsRemainder == 0) {
            System.out.println(numIn + " IS NOT A PRIME");
        } else {
            System.out.println(numIn + " IS A PRIME");
        }
        numIn = "";
    }

    private static void getInput() {
        while (!isNumeric(numIn) && !userWantsToQuit()) {
            System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
            System.out.println("(OR ENTER X TO QUIT).");
            numIn = scanner.nextLine().trim();
        }
        if (isNumeric(numIn)) {
            numInInt = Integer.parseInt(numIn);
            wsRemainder = 1;
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        if (str.length() != 4) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private static boolean userWantsToQuit() {
        return numIn.equals("x") || numIn.equals("X") || numIn.equals("XX") || numIn.equals("xx");
    }
}