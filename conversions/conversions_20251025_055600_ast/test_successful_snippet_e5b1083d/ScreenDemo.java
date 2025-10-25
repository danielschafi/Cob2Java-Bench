import java.util.Scanner;

public class ScreenDemo {
    private static final int BLACK = 0;
    private static final int BLUE = 1;
    private static final int GREEN = 2;
    private static final int CYAN = 3;
    private static final int RED = 4;
    private static final int MAGENTA = 5;
    private static final int YELLOW = 6;
    private static final int WHITE = 7;

    private static String lastName = new String(new char[10]).replace('\0', ' ');
    private static String firstName = new String(new char[10]).replace('\0', ' ');
    private static String socSecNo = new String(new char[9]).replace('\0', ' ');
    private static String comment = new String(new char[25]).replace('\0', ' ');
    private static String aMessage = new String(new char[35]).replace('\0', ' ');
    private static int field1Color = MAGENTA;
    private static char hek = ' ';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBlankScreen();
        field1Color = MAGENTA;

        screenLoop:
        while (true) {
            displayEntryScreen();
            acceptEntryScreen(scanner);

            if (lastName.charAt(0) != 'q') {
                field1Color = GREEN;
                aMessage = "Last name must begin with q to exit";
                displayEntryScreen();
                continue screenLoop;
            }

            displayExitScreen();
            acceptExitScreen(scanner);

            break;
        }

        scanner.close();
    }

    private static void displayBlankScreen() {
        System.out.println("\033[47m" + " ".repeat(80) + "\033[0m");
    }

    private static void displayEntryScreen() {
        System.out.println("\033[47m" + " ".repeat(80) + "\033[0m");
        System.out.println("    Enter Last Name: " + "\033[" + (field1Color + 30) + "m" + lastName + "\033[0m");
        System.out.println("    Enter First Name: " + "\033[34m" + firstName + "\033[0m");
        System.out.println("    Enter Social Sec: " + "\033[31;7m" + socSecNo + "\033[0m");
        System.out.println("    Enter Comment: " + "\033[33m" + comment + "\033[0m");
        System.out.println("          " + aMessage);
    }

    private static void acceptEntryScreen(Scanner scanner) {
        System.out.print("    Enter Last Name: ");
        lastName = scanner.nextLine().substring(0, Math.min(10, scanner.nextLine().length()));
        System.out.print("    Enter First Name: ");
        firstName = scanner.nextLine().substring(0, Math.min(10, scanner.nextLine().length()));
        System.out.print("    Enter Social Sec: ");
        socSecNo = scanner.nextLine().substring(0, Math.min(9, scanner.nextLine().length()));
        System.out.print("    Enter Comment: ");
        comment = scanner.nextLine().substring(0, Math.min(25, scanner.nextLine().length()));
    }

    private static void displayExitScreen() {
        System.out.println("\033[43m" + " ".repeat(80) + "\033[0m");
        System.out.println("    You entered:");
        System.out.println("      Last name: " + lastName.trim());
        System.out.println("      First name: " + firstName.trim());
        System.out.println("      Social Sec: " + socSecNo.trim());
        System.out.println("      Comment: " + comment.trim());
        System.out.println("hit enter");
    }

    private static void acceptExitScreen(Scanner scanner) {
        hek = scanner.nextLine().charAt(0);
    }
}