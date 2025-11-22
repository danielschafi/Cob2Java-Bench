import java.util.Scanner;

public class IsNumericTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String wsUserInput;
        String wsUserInputJustified;

        processPlain(scanner);
        processZeroFill(scanner);
        processTrim(scanner);

        scanner.close();
    }

    public static void processPlain(Scanner scanner) {
        System.out.print("(plain) Enter a value: ");
        String wsUserInput = scanner.nextLine();

        if (isNumeric(wsUserInput)) {
            System.out.println(wsUserInput + " is numeric!");
        } else {
            System.out.println(wsUserInput + " is not numeric.");
        }
    }

    public static void processZeroFill(Scanner scanner) {
        System.out.print("(right justify, zero fill) Enter another value: ");
        String wsUserInputJustified = scanner.nextLine();

        wsUserInputJustified = wsUserInputJustified.trim();
        wsUserInputJustified = String.format("%10s", wsUserInputJustified).replace(' ', '0');

        if (isNumeric(wsUserInputJustified)) {
            System.out.println(wsUserInputJustified + " is numeric!");
        } else {
            System.out.println(wsUserInputJustified + " is not numeric.");
        }
    }

    public static void processTrim(Scanner scanner) {
        System.out.print("(trim) Enter a third value: ");
        String wsUserInput = scanner.nextLine();

        wsUserInput = wsUserInput.trim();

        if (isNumeric(wsUserInput)) {
            System.out.println(wsUserInput + " is numeric!");
        } else {
            System.out.println(wsUserInput + " is not numeric.");
        }
    }

    public static boolean isNumeric(String str) {
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