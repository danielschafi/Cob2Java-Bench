import java.util.Scanner;
import java.text.DecimalFormat;

public class RPN {
    private String lineIn;
    private int ip = 1;
    private String cInNum;
    private double[] stack = new double[50];
    private int sp = 1;
    private String operator;
    private double value1;
    private double value2;
    private double result;
    private int idx;
    private DecimalFormat formatNum = new DecimalFormat("0.0000000");
    
    public static void main(String[] args) {
        RPN rpn = new RPN();
        rpn.mainProgram();
    }
    
    private void mainProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the RPN Equation: ");
        lineIn = scanner.nextLine();
        
        String[] tokens = lineIn.split(" ");
        
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            
            operator = token;
            doOperation();
            showStack();
        }
        
        System.out.println("End Result: " + formatNumber(stack[sp - 1]));
        scanner.close();
    }
    
    private void doOperation() {
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
    
    private void showStack() {
        System.out.print("STACK: ");
        for (int i = 0; i < sp - 1; i++) {
            if (stack[i] < 0) {
                System.out.print("    -" + formatNumber(Math.abs(stack[i])));
            } else {
                System.out.print(formatNumber(stack[i]));
            }
        }
        System.out.println(" ");
    }
    
    private void push() {
        stack[sp - 1] = result;
        sp++;
    }
    
    private void pop() {
        sp--;
        value1 = stack[sp - 1];
        sp--;
        value2 = stack[sp - 1];
    }
    
    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private String formatNumber(double num) {
        String formatted = formatNum.format(num);
        return formatted.replaceAll("0+$", "").replaceAll("\\.$", "");
    }
}