import java.util.Scanner;

public class RPN {
    private static final int STACK_SIZE = 50;
    private double[] stack = new double[STACK_SIZE];
    private int sp = 1;
    private String operator;
    private double value1;
    private double value2;
    private double result;
    private int idx;
    private String formatNum;

    public static void main(String[] args) {
        RPN rpn = new RPN();
        rpn.mainProgram();
    }

    public void mainProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the RPN Equation: ");
        String lineIn = scanner.nextLine();
        scanner.close();

        int ip = 1;
        while (ip <= lineIn.length()) {
            int spaceIndex = lineIn.indexOf(' ', ip - 1);
            if (spaceIndex == -1) {
                spaceIndex = lineIn.length();
            }
            String cInNum = lineIn.substring(ip - 1, spaceIndex);
            operator = cInNum;
            doOperation();
            showStack();
            ip = spaceIndex + 1;
        }

        formatNum = String.format("%07.7f", result);
        System.out.println("End Result: " + formatNum);
    }

    public void doOperation() {
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
                try {
                    result = Double.parseDouble(operator);
                    push();
                } catch (NumberFormatException e) {
                    // Handle invalid input if necessary
                }
                break;
        }
    }

    public void showStack() {
        System.out.print("STACK: ");
        for (idx = 1; idx < sp; idx++) {
            formatNum = String.format("%07.7f", stack[idx]);
            if (stack[idx] < 0) {
                System.out.print("    -" + formatNum.trim());
            } else {
                System.out.print(formatNum);
            }
        }
        System.out.println();
    }

    public void push() {
        stack[sp] = result;
        sp++;
    }

    public void pop() {
        sp--;
        value1 = stack[sp];
        sp--;
        value2 = stack[sp];
    }
}