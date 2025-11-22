import java.util.Scanner;

public class SWAP_DEMO {
    
    private static class SwapResult {
        String val1;
        String val2;
        
        SwapResult(String val1, String val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String val1;
        String val2;
        
        System.out.print("Enter a Value: ");
        val1 = scanner.nextLine();
        if (val1.length() > 72) {
            val1 = val1.substring(0, 72);
        }
        val1 = String.format("%-72s", val1);
        
        System.out.print("Enter another Value: ");
        val2 = scanner.nextLine();
        if (val2.length() > 72) {
            val2 = val2.substring(0, 72);
        }
        val2 = String.format("%-72s", val2);
        
        System.out.println(" ");
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());
        
        SwapResult result = SWAP(val1, val2);
        val1 = result.val1;
        val2 = result.val2;
        
        System.out.println(" ");
        System.out.println("After SWAP ");
        System.out.println(" ");
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());
        
        scanner.close();
    }
    
    private static SwapResult SWAP(String field1, String field2) {
        String temp = field1;
        field1 = field2;
        field2 = temp;
        return new SwapResult(field1, field2);
    }
}