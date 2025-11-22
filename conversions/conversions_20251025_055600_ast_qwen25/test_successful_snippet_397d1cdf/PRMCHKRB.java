import java.util.Scanner;

public class PRMCHKRB {
    private String numIn;
    private int numInInt;
    private int wsQuotient;
    private int wsRemainder;
    private int wsDivisor;

    public PRMCHKRB() {
        numIn = "";
        wsQuotient = 0;
        wsRemainder = 1;
        wsDivisor = 0;
    }

    public static void main(String[] args) {
        PRMCHKRB program = new PRMCHKRB();
        program.driver();
    }

    public void driver() {
        System.out.println("Prime Number Checking Program");
        getUserInput();
        while (!userWantsToQuit()) {
            checkNumber();
            getUserInput();
        }
        System.out.println("EXIT");
    }

    public void checkNumber() {
        divisionCalc();
        primeChk();
    }

    public void divisionCalc() {
        wsRemainder = 1;
        for (wsDivisor = 2; wsRemainder != 0 && wsDivisor < numInInt - 1; wsDivisor++) {
            wsRemainder = numInInt % wsDivisor;
        }
    }

    public void primeChk() {
        if (wsRemainder == 0) {
            System.out.println(numIn + " IS NOT A PRIME");
        } else {
            System.out.println(numIn + " IS A PRIME");
        }
        numIn = "";
    }

    public void getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (!isNumeric(numIn) && !userWantsToQuit()) {
            System.out.println("ENTER INTEGER 0000-9999 (WITH LEADING ZEROES)");
            System.out.println("(OR ENTER X TO QUIT).");
            numIn = scanner.nextLine();
        }
        if (isNumeric(numIn)) {
            numInInt = Integer.parseInt(numIn);
        }
    }

    public boolean isNumeric(String str) {
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

    public boolean userWantsToQuit() {
        return numIn.equalsIgnoreCase("x") || numIn.equalsIgnoreCase("xx");
    }
}