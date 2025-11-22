import java.util.Scanner;

public class Chapt10d {
    private static final String SPACES = "                    ";
    private static final String F1_PRESSED = "\u0001";
    private static String lastName = SPACES;
    private static String firstName = SPACES;
    private static String email = SPACES;
    private static String functionKey = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayAndAcceptScreen(scanner);
            if (functionKey.equals(F1_PRESSED)) {
                break;
            }
            processDataFields();
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
        System.out.print("Function Key: ");
        functionKey = scanner.nextLine().trim();

        if (functionKey.equals(F1_PRESSED)) {
            return;
        } else {
            processDataFields();
        }
    }

    private static void processDataFields() {
        if (!lastName.equals(SPACES)) {
            processLastName();
        } else {
            lastName = "********************";
        }

        if (!firstName.equals(SPACES)) {
            processFirstName();
        } else {
            firstName = "********************";
        }

        if (!email.equals(SPACES)) {
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
        lastName = SPACES;
        firstName = SPACES;
        email = SPACES;
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
            System.out.print("Function Key: ");
            functionKey = scanner.nextLine().trim();

            if (functionKey.equals(F1_PRESSED)) {
                System.exit(0);
            }

            if (!lastName.equals(SPACES)) {
                processLastName();
            } else {
                lastName = "********************";
            }

            if (!firstName.equals(SPACES)) {
                processFirstName();
            } else {
                firstName = "********************";
            }

            if (!email.equals(SPACES)) {
                processEmail();
            } else {
                email = "******************************";
            }
        }
    }
}