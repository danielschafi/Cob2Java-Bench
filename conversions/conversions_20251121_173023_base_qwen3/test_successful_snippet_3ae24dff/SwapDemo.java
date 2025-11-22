import java.util.Scanner;

public class SwapDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String val1 = new String(new char[72]);
        String val2 = new String(new char[72]);
        
        System.out.print("Enter a Value: ");
        val1 = scanner.nextLine();
        System.out.print("Enter another Value: ");
        val2 = scanner.nextLine();
        
        System.out.println();
        System.out.println("First value: " + trim(val1));
        System.out.println("Second value: " + trim(val2));
        
        swap(val1, val2);
        
        System.out.println();
        System.out.println("After SWAP");
        System.out.println();
        System.out.println("First value: " + trim(val1));
        System.out.println("Second value: " + trim(val2));
        
        scanner.close();
    }
    
    public static void swap(StringBuilder field1, StringBuilder field2) {
        String temp = field1.toString();
        field1.replace(0, field1.length(), field2.toString());
        field2.replace(0, field2.length(), temp);
    }
    
    public static String trim(String str) {
        return str.trim();
    }
}