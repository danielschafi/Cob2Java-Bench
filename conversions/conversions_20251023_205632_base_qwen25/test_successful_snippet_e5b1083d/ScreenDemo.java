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

        while (true) {
            displayEntryScreen();
            acceptEntryScreen(scanner);

            if (!lastName.trim().isEmpty() && lastName.charAt(0) != 'q') {
                field1Color = GREEN;
                aMessage = "Last name must begin with q to exit";
                displayEntryScreen();
                continue;
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
        System.out.println("\033[47m" + " ".repeat(4) + "Enter Last Name" + " ".repeat(61) + "\033[0m");
        System.out.print("\033[47m" + " ".repeat(4) + "\033[" + (field1Color + 30) + "m" + lastName + "\033[0m" + " ".repeat(61) + "\033[0m\n");
        System.out.println("\033[47m" + " ".repeat(4) + "Enter First Name" + " ".repeat(58) + "\033[0m");
        System.out.print("\033[47m" + " ".repeat(4) + "\033[34;1m" + firstName + "\033[0m" + " ".repeat(58) + "\033[0m\n");
        System.out.println("\033[47m" + " ".repeat(4) + "Enter Social Sec" + " ".repeat(56) + "\033[0m");
        System.out.print("\033[47m" + " ".repeat(4) + "\033[31;7m" + socSecNo + "\033[0m" + " ".repeat(56) + "\033[0m\n");
        System.out.println("\033[47m" + " ".repeat(4) + "Enter Comment" + " ".repeat(59) + "\033[0m");
        System.out.print("\033[47m" + " ".repeat(4) + "\033[33;1m" + comment + "\033[0m" + " ".repeat(59) + "\033[0m\n");
        System.out.println("\033[47m" + " ".repeat(4) + aMessage + " ".repeat(76 - aMessage.length()) + "\033[0m");
    }

    private static void acceptEntryScreen(Scanner scanner) {
        System.out.print("\033[47m" + " ".repeat(4) + "\033[" + (field1Color + 30) + "m" + lastName + "\033[0m" + " ".repeat(61) + "\033[0m\r");
        lastName = scanner.nextLine().trim().substring(0, Math.min(10, scanner.nextLine().length()));
        System.out.print("\033[47m" + " ".repeat(4) + "\033[34;1m" + firstName + "\033[0m" + " ".repeat(58) + "\033[0m\r");
        firstName = scanner.nextLine().trim().substring(0, Math.min(10, scanner.nextLine().length()));
        System.out.print("\033[47m" + " ".repeat(4) + "\033[31;7m" + socSecNo + "\033[0m" + " ".repeat(56) + "\033[0m\r");
        socSecNo = scanner.nextLine().trim().substring(0, Math.min(9, scanner.nextLine().length()));
        System.out.print("\033[47m" + " ".repeat(4) + "\033[33;1m" + comment + "\033[0m" + " ".repeat(59) + "\033[0m\r");
        comment = scanner.nextLine().trim().substring(0, Math.min(25, scanner.nextLine().length()));
    }

    private static void displayExitScreen() {
        System.out.println("\033[43m" + " ".repeat(80) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(4) + "You entered:" + " ".repeat(66) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(4) + "  Last name:" + " ".repeat(55) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(17) + lastName.trim() + " ".repeat(58 - lastName.trim().length()) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(4) + "  First name:" + " ".repeat(54) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(17) + firstName.trim() + " ".repeat(58 - firstName.trim().length()) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(4) + "  Social Sec:" + " ".repeat(53) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(17) + socSecNo.trim() + " ".repeat(58 - socSecNo.trim().length()) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(4) + "  Comment:" + " ".repeat(58) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(17) + comment.trim() + " ".repeat(58 - comment.trim().length()) + "\033[0m");
        System.out.println("\033[43m" + " ".repeat(23) + "hit enter" + " ".repeat(54) + "\033[0m");
        System.out.print("\033[43m" + " ".repeat(39) + hek + " ".repeat(39) + "\033[0m\r");
    }

    private static void acceptExitScreen(Scanner scanner) {
        hek = scanner.nextLine().charAt(0);
    }
}