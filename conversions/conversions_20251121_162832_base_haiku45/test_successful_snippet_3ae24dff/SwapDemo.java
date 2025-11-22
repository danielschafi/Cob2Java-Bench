import java.util.Scanner;

public class SwapDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a Value: ");
        String val1 = scanner.nextLine();
        
        System.out.print("Enter another Value: ");
        String val2 = scanner.nextLine();
        
        System.out.println(" ");
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());
        
        String[] values = swap(val1, val2);
        val1 = values[0];
        val2 = values[1];
        
        System.out.println(" ");
        System.out.println("After SWAP ");
        System.out.println(" ");
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());
        
        scanner.close();
    }
    
    public static String[] swap(String field1, String field2) {
        String temp = field1;
        field1 = field2;
        field2 = temp;
        
        return new String[]{field1, field2};
    }
}