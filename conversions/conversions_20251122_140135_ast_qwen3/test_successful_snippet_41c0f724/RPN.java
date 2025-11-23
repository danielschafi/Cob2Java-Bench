import java.util.*;

public class RPN {
    private static String LineIn;
    private static int IP = 1;
    private static String CInNum;
    
    private static double[] Stack = new double[50];
    private static int SP = 1;
    private static char Operator;
    private static double Value1;
    private static double Value2;
    private static double Result;
    private static int Idx;
    private static String FormatNum;
    private static char Zip;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the RPN Equation: ");
        LineIn = scanner.nextLine();
        
        while (IP <= LineIn.length()) {
            int spaceIndex = LineIn.indexOf(' ', IP - 1);
            if (spaceIndex == -1) {
                spaceIndex = LineIn.length();
            }
            CInNum = LineIn.substring(IP - 1, spaceIndex);
            IP = spaceIndex + 1;
            
            Operator = CInNum.charAt(0);
            
            doOperation();
            showStack();
        }
        
        System.out.println("End Result: " + formatNumber(Result));
    }
    
    private static void doOperation() {
        switch (Operator) {
            case '+':
                pop();
                Result = Value2 + Value1;
                push();
                break;
            case '-':
                pop();
                Result = Value2 - Value1;
                push();
                break;
            case '*':
                pop();
                Result = Value2 * Value1;
                push();
                break;
            case '/':
                pop();
                Result = Value2 / Value1;
                push();
                break;
            case '^':
                pop();
                Result = Math.pow(Value2, Value1);
                push();
                break;
            default:
                if (Character.isDigit(Operator)) {
                    Result = Double.parseDouble(CInNum);
                    push();
                }
                break;
        }
    }
    
    private static void showStack() {
        System.out.print("STACK: ");
        for (int i = 1; i < SP; i++) {
            System.out.print(formatNumber(Stack[i]) + " ");
        }
        System.out.println();
    }
    
    private static void push() {
        Stack[SP] = Result;
        SP++;
    }
    
    private static void pop() {
        SP--;
        Value1 = Stack[SP];
        SP--;
        Value2 = Stack[SP];
    }
    
    private static String formatNumber(double num) {
        if (num < 0) {
            return "-" + String.format("%10.6f", Math.abs(num)).trim();
        } else {
            return String.format("%10.6f", num).trim();
        }
    }
}