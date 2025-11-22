import java.util.Scanner;

public class NthRoot {
    private static Scanner scanner = new Scanner(System.in);
    
    private static double root;
    private static double num;
    private static double precision;
    private static double temp0;
    private static double temp1;
    
    public static void main(String[] args) {
        while (true) {
            getInput();
            
            if (precision == 0.0) {
                precision = 0.001;
            }
            
            computeRoot();
            
            System.out.println("   Number           Root           Precision.");
            System.out.printf("%10.5f     %5.0f     %10.9f%n", num, root, precision);
            System.out.println(" ");
            System.out.printf("The Root is: %10.5f%n", temp1);
        }
    }
    
    private static void getInput() {
        System.out.print("Input Base Number: ");
        num = scanner.nextDouble();
        
        if (num == 0.0) {
            System.out.println("Good Bye.");
            System.exit(0);
        }
        
        System.out.print("Input Root: ");
        root = scanner.nextDouble();
        
        System.out.print("Input Desired Precision: ");
        precision = scanner.nextDouble();
    }
    
    private static void computeRoot() {
        temp0 = root;
        temp1 = num / root;
        
        while (Math.abs(temp0 - temp1) >= precision) {
            temp0 = temp1;
            temp1 = ((root - 1.0) * temp1 + num / Math.pow(temp1, root - 1.0)) / root;
        }
    }
}