import java.util.Scanner;

public class SwapDemo {
    private static String val1;
    private static String val2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a Value: ");
        val1 = scanner.nextLine();
        
        System.out.print("Enter another Value: ");
        val2 = scanner.nextLine();
        
        System.out.println();
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());
        
        swap();
        
        System.out.println();
        System.out.println("After SWAP ");
        System.out.println();
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());
        
        scanner.close();
    }
    
    private static void swap() {
        String temp = val1;
        val1 = val2;
        val2 = temp;
    }
}