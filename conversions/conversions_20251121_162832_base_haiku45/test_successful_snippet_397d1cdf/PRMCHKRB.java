import java.util.Scanner;

public class PRMCHKRB {
    private String numIn = "";
    private int numInInt = 0;
    private int wsQuotient = 0;
    private int wsRemainder = 1;
    private int wsDivisor = 0;
    private boolean userWantsToQuit = false;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PRMCHKRB program = new PRMCHKRB();
        program.driver();
    }

    private void driver() {
        System.out.println("Prime Number Checking Program");
        getInput();
        while (!userWantsToQuit) {
            checkNumber();
        }
        System.out.println("EXIT");
    }

    private void checkNumber() {
        divisionCalc();
        primeCheck();
        getInput();
    }

    private void divisionCalc() {
        wsRemainder = 1;
        wsRemainder += 1;
        wsDivisor = 2;
        while (wsRemainder != 0 && wsDivisor < (numInInt - 1)) {
            wsRemainder = numInInt % wsDivisor;
            wsDivisor++;
        }
    }

    private void primeCheck() {
        if (wsRemainder == 0) {
            System.out.println(numIn + " IS NOT A PRIME");
        } else {
            System.out.println(numIn + " IS A PRIME");
        }
        numIn = "";
    }

    private void getInput() {
        userWantsToQuit = false;
        boolean validInput = false;

        while (!validInput && !userWantsToQuit) {
            System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
            System.out.println("(OR ENTER X TO QUIT).");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("x") || input.equalsIgnoreCase("xx")) {
                userWantsToQuit = true;
                validInput = true;
            } else if (isNumeric(input) && input.length() <= 4) {
                numIn = String.format("%04d", Integer.parseInt(input));
                numInInt = Integer.parseInt(numIn);
                validInput = true;
            }
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}