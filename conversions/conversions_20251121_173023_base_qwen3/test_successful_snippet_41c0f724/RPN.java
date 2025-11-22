import java.util.*;

public class RPN {
    private static final int MAX_STACK_SIZE = 50;
    private static final Scanner scanner = new Scanner(System.in);
    
    private static String lineIn;
    private static int ip = 1;
    private static String cInNum;
    private static double[] stack = new double[MAX_STACK_SIZE];
    private static int sp = 1;
    private static String operator;
    private static double value1;
    private static double value2;
    private static double result;
    private static int idx;
    private static String formatNum;
    private static char zip;

    public static void main(String[] args) {
        System.out.print("Enter the RPN Equation: ");
        lineIn = scanner.nextLine();
        
        while (ip <= lineIn.length()) {
            // Extract next token
            int spaceIndex = lineIn.indexOf(' ', ip - 1);
            if (spaceIndex == -1) {
                spaceIndex = lineIn.length();
            }
            
            cInNum = lineIn.substring(ip - 1, spaceIndex).trim();
            ip = spaceIndex + 1;
            
            operator = cInNum;
            doOperation();
            showStack();
        }
        
        System.out.println("End Result: " + formatNumber(result));
    }
    
    private static void doOperation() {
        switch (operator) {
            case "+":
                pop();
                result = value2 + value1;
                push();
                break;
                
            case "-":
                pop();
                result = value2 - value1;
                push();
                break;
                
            case "*":
                pop();
                result = value2 * value1;
                push();
                break;
                
            case "/":
                pop();
                result = value2 / value1;
                push();
                break;
                
            case "^":
                pop();
                result = Math.pow(value2, value1);
                push();
                break;
                
            default:
                if (isNumeric(operator)) {
                    result = Double.parseDouble(operator);
                    push();
                }
                break;
        }
    }
    
    private static void showStack() {
        System.out.print("STACK: ");
        for (int i = 1; i < sp; i++) {
            System.out.print(formatNumber(stack[i]) + " ");
        }
        System.out.println();
    }
    
    private static void push() {
        stack[sp] = result;
        sp++;
    }
    
    private static void pop() {
        sp--;
        value1 = stack[sp];
        sp--;
        value2 = stack[sp];
    }
    
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static String formatNumber(double num) {
        if (num == (long) num) {
            return String.format("%d", (long) num);
        } else {
            return String.format("%.6f", num).replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
}