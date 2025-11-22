public class PRMCHKRB {
    private static String NUM_IN;
    private static int NUM_IN_INT;
    private static int WS_QUOTIENT = 0;
    private static int WS_REMAINDER = 1;
    private static int WS_DIVISOR = 0;

    public static void main(String[] args) {
        System.out.println("Prime Number Checking Program");
        get userInput = new get();
        userInput.getInput();
        checkNumber();
        while (!isUserWantsToQuit()) {
            userInput.getInput();
            checkNumber();
        }
        System.out.println("EXIT");
    }

    private static void checkNumber() {
        divisionCalc();
        primeChk();
    }

    private static void divisionCalc() {
        WS_REMAINDER = 1;
        WS_DIVISOR = 0;
        WS_DIVISOR++;
        while ((WS_REMAINDER != 0) && (WS_DIVISOR != (NUM_IN_INT - 1))) {
            WS_REMAINDER = NUM_IN_INT % WS_DIVISOR;
            WS_DIVISOR++;
        }
    }

    private static void primeChk() {
        if (WS_REMAINDER == 0) {
            System.out.println(NUM_IN + " IS NOT A PRIME");
        } else {
            System.out.println(NUM_IN + " IS A PRIME");
        }
        NUM_IN = "";
    }

    private static boolean isUserWantsToQuit() {
        return NUM_IN.equals("x") || NUM_IN.equals("X") || NUM_IN.equals("XX") || NUM_IN.equals("xx");
    }

    private static class get {
        public void getInput() {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            do {
                System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
                System.out.println("(OR ENTER X TO QUIT).");
                NUM_IN = scanner.nextLine();
            } while (!isNumeric(NUM_IN) && !isUserWantsToQuit());
        }

        private boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
}