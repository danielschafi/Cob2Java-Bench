import java.util.Scanner;

public class AcceptExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String wsInput = new String(new char[16]);

        System.out.print("Simple accept. Enter a value: ");
        wsInput = scanner.nextLine().trim();
        System.out.println("You entered: " + wsInput);

        System.out.println("Press any key to enter screen mode.");
        scanner.nextLine();

        System.out.print("Enter value or wait 3 seconds: ");
        wsInput = scanner.nextLine().trim();
        System.out.println("You entered: " + wsInput);

        System.out.print("Enter 16 chars to auto skip: ");
        wsInput = scanner.nextLine().trim();
        System.out.println("You entered: " + wsInput);

        System.out.print("Enter a value (no echo): ");
        wsInput = scanner.nextLine().trim();
        System.out.println("You entered: " + wsInput);

        System.out.print("Enter a value: ");
        wsInput = scanner.nextLine().trim().toUpperCase();
        System.out.println("You entered: " + wsInput);

        scanner.close();
    }
}