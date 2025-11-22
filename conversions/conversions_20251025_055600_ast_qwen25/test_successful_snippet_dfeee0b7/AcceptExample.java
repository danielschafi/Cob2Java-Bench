import java.util.Scanner;

public class AcceptExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String wsInput = new String(new char[16]);

        // Simple accept syntax
        System.out.print("Simple accept. Enter a value: ");
        wsInput = scanner.nextLine().trim();
        System.out.println("You entered: " + wsInput);

        // Accept omitted (simulated by waiting for any key press)
        System.out.println("Press any key to enter screen mode.");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Timeout (simulated using a thread sleep)
        System.out.print("Enter value or wait 3 seconds: ");
        Thread timeoutThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("You entered: ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        timeoutThread.start();
        wsInput = scanner.nextLine().trim();
        timeoutThread.interrupt();
        System.out.println("You entered: " + wsInput);

        // Auto-skip (simulated by reading up to 16 characters)
        System.out.print("Enter 16 chars to auto skip: ");
        wsInput = scanner.nextLine().trim().substring(0, Math.min(16, wsInput.length()));
        System.out.println("You entered: " + wsInput);

        // No-echo (simulated by not displaying input)
        System.out.print("Enter a value (no echo): ");
        wsInput = scanner.nextLine().trim();
        System.out.println("You entered: " + wsInput);

        // Upper (convert input to uppercase)
        System.out.print("Enter a value: ");
        wsInput = scanner.nextLine().trim().toUpperCase();
        System.out.println("You entered: " + wsInput);

        scanner.close();
    }
}