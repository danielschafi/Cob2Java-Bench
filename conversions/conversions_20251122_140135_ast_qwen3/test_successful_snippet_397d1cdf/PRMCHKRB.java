import java.util.Scanner;

public class PRMCHKRB {
    private static String NUM_IN;
    private static int NUM_IN_INT;
    private static int WS_QUOTIENT = 0;
    private static int WS_REMAINDER = 1;
    private static boolean NOT_PRIME_NUMBER = false;
    private static int WS_DIVISOR = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Prime Number Checking Program");
        get userInput = new get();
        userInput.getUserInput();
        
        while (!USER_WANTS_TO_QUIT()) {
            checkNumber();
            userInput.getUserInput();
        }
        System.out.println("EXIT");
    }

    private static void checkNumber() {
        divisionCalc();
        primeCheck();
    }

    private static void divisionCalc() {
        WS_REMAINDER = 1;
        WS_DIVISOR = 0;
        for (int i = 2; i <= (NUM_IN_INT - 1); i++) {
            WS_DIVISOR = i;
            WS_REMAINDER = NUM_IN_INT % WS_DIVISOR;
            if (WS_REMAINDER == 0) {
                break;
            }
        }
    }

    private static void primeCheck() {
        if (NOT_PRIME_NUMBER) {
            System.out.println(NUM_IN + " IS NOT A PRIME");
        } else {
            System.out.println(NUM_IN + " IS A PRIME");
        }
        NUM_IN = "";
    }

    private static boolean USER_WANTS_TO_QUIT() {
        return NUM_IN.equals("x") || NUM_IN.equals("X") || NUM_IN.equals("XX") || NUM_IN.equals("xx");
    }

    private static class get {
        public void getUserInput() {
            while (!(isNumeric(NUM_IN) || USER_WANTS_TO_QUIT())) {
                System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
                System.out.println("(OR ENTER X TO QUIT).");
                NUM_IN = scanner.nextLine();
            }
            if (!USER_WANTS_TO_QUIT()) {
                NUM_IN_INT = Integer.parseInt(NUM_IN);
            }
        }

        private boolean isNumeric(String str) {
            if (str == null || str.isEmpty()) {
                return false;
            }
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}