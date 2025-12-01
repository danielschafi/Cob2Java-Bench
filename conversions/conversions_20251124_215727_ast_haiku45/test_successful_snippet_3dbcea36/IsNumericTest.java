import java.util.Scanner;

public class IsNumericTest {
    private static String wsUserInput = "";
    private static String wsUserInputJustified = "";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        processPlain();
        processZeroFill();
        processTrim();
        System.exit(0);
    }

    private static void processPlain() {
        System.out.print("(plain) Enter a value: ");
        wsUserInput = scanner.nextLine();

        if (isNumeric(wsUserInput)) {
            System.out.println(wsUserInput + " is numeric!");
        } else {
            System.out.println(wsUserInput + " is not numeric.");
        }
    }

    private static void processZeroFill() {
        System.out.print("(right justify, zero fill) Enter another value: ");
        wsUserInputJustified = scanner.nextLine();

        wsUserInputJustified = rightJustifyAndFillZeros(wsUserInputJustified);

        if (isNumeric(wsUserInputJustified)) {
            System.out.println(wsUserInputJustified + " is numeric!");
        } else {
            System.out.println(wsUserInputJustified + " is not numeric.");
        }
    }

    private static void processTrim() {
        System.out.print("(trim) Enter a third value: ");
        wsUserInput = scanner.nextLine();

        String trimmed = wsUserInput.trim();
        if (isNumeric(trimmed)) {
            System.out.println(trimmed + " is numeric!");
        } else {
            System.out.println(trimmed + " is not numeric.");
        }
    }

    private static boolean isNumeric(String str) {
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

    private static String rightJustifyAndFillZeros(String input) {
        String padded = String.format("%10s", input).replace(' ', '0');
        return padded;
    }
}