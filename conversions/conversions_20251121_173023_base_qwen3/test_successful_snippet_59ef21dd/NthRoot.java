import java.util.Scanner;
import java.lang.Math;

public class NthRoot {
    private static Scanner scanner = new Scanner(System.in);
    
    private static double num;
    private static int root;
    private static double precision;
    private static double temp0;
    private static double temp1;
    private static String field1;
    private static String field2;
    private static String field3;
    private static String header = "   Number           Root           Precision.";
    private static String dispRoot;
    
    public static void main(String[] args) {
        while (true) {
            getInput();
            
            if (precision == 0.0) {
                precision = 0.001;
            }
            
            computeRoot();
            
            field2 = String.format("%5d", root);
            field1 = String.format("%8.5f", num);
            field3 = String.format("%.9f", precision);
            
            System.out.println(header);
            System.out.println(field1 + "    " + field2 + "    " + field3);
            System.out.println(" ");
            
            dispRoot = String.format("%8.5f", temp1);
            System.out.println("The Root is: " + dispRoot);
        }
    }
    
    public static void getInput() {
        System.out.print("Input Base Number: ");
        num = scanner.nextDouble();
        
        if (num == 0.0) {
            System.out.println("Good Bye.");
            System.exit(0);
        }
        
        System.out.print("Input Root: ");
        root = scanner.nextInt();
        
        System.out.print("Input Desired Precision: ");
        precision = scanner.nextDouble();
    }
    
    public static void computeRoot() {
        temp0 = root;
        temp1 = num / root;
        
        while (Math.abs(temp0 - temp1) >= precision) {
            temp0 = temp1;
            temp1 = (((root - 1.0) * temp1) + (num / Math.pow(temp1, (root - 1.0)))) / root;
        }
    }
}