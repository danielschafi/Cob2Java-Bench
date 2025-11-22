import java.util.Scanner;

public class Chapt10d {
    private static int acceptStatus;
    private static char functionKey;
    private static String lastName = "                    ";
    private static String firstName = "                    ";
    private static String email = "                              ";
    private static final char F1_PRESSED = 0x01;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayAndAcceptScreen(scanner);
            if (functionKey == F1_PRESSED) {
                break;
            }
        }

        initializeScreenItems();
        processScreen(scanner);
    }

    private static void displayAndAcceptScreen(Scanner scanner) {
        System.out.println("Name and E-mail Entry");
        System.out.println("E-mail: " + email);
        System.out.println("  Last: " + lastName);
        System.out.println(" First: " + firstName);
        System.out.println("Press F1 to Exit");

        System.out.print("E-mail: ");
        email = scanner.nextLine().trim();
        System.out.print("  Last: ");
        lastName = scanner.nextLine().trim();
        System.out.print(" First: ");
        firstName = scanner.nextLine().trim();

        System.out.print("Function Key (F1 = 1): ");
        acceptStatus = scanner.nextInt();
        functionKey = (char) acceptStatus;
        scanner.nextLine();  // Consume newline

        if (functionKey != F1_PRESSED) {
            processDataFields();
        }
    }

    private static void processDataFields() {
        if (!lastName.trim().isEmpty()) {
            processLastName();
        } else {
            lastName = "********************";
        }
        if (!firstName.trim().isEmpty()) {
            processFirstName();
        } else {
            firstName = "********************";
        }
        if (!email.trim().isEmpty()) {
            processEmail();
        } else {
            email = "******************************";
        }
    }

    private static void processLastName() {
        lastName = lastName.toUpperCase();
    }

    private static void processFirstName() {
        firstName = firstName.toUpperCase();
    }

    private static void processEmail() {
        email = email.toLowerCase();
    }

    private static void initializeScreenItems() {
        lastName = "                    ";
        firstName = "                    ";
        email = "                              ";
    }

    private static void processScreen(Scanner scanner) {
        while (true) {
            System.out.println("Name and E-mail Entry");
            System.out.println("E-mail: " + email);
            System.out.println("  Last: " + lastName);
            System.out.println(" First: " + firstName);
            System.out.println("Press F1 to Exit");

            System.out.print("E-mail: ");
            email = scanner.nextLine().trim();
            System.out.print("  Last: ");
            lastName = scanner.nextLine().trim();
            System.out.print(" First: ");
            firstName = scanner.nextLine().trim();

            System.out.print("Function Key (F1 = 1): ");
            acceptStatus = scanner.nextInt();
            functionKey = (char) acceptStatus;
            scanner.nextLine();  // Consume newline

            if (functionKey == F1_PRESSED) {
                break;
            }

            if (!lastName.trim().isEmpty()) {
                processLastName();
            } else {
                lastName = "********************";
            }
            checkFirstName(scanner);
        }
    }

    private static void checkFirstName(Scanner scanner) {
        if (!firstName.trim().isEmpty()) {
            processFirstName();
        } else {
            firstName = "********************";
        }
        checkEmail(scanner);
    }

    private static void checkEmail(Scanner scanner) {
        if (!email.trim().isEmpty()) {
            processEmail();
        } else {
            email = "******************************";
        }
        processScreen(scanner);
    }
}