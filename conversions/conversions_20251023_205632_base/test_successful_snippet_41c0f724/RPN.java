import java.util.Scanner;

public class RPN {
    private static final int MAX_STACK_SIZE = 50;
    private static double[] stack = new double[MAX_STACK_SIZE];
    private static int sp = 1;
    private static String lineIn;
    private static int ip = 1;
    private static String cInNum;
    private static char operator;
    private static double value1;
    private static double value2;
    private static double result;
    private static int idx;
    private static String formatNum;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter the RPN Equation: ");
        lineIn = scanner.nextLine();

        while (ip <= lineIn.length()) {
            int spaceIndex = lineIn.indexOf(' ', ip - 1);
            if (spaceIndex == -1) {
                cInNum = lineIn.substring(ip - 1);
                ip = lineIn.length() + 1;
            } else {
                cInNum = lineIn.substring(ip - 1, spaceIndex);
                ip = spaceIndex + 2;
            }

            operator = cInNum.charAt(0);

            doOperation();

            showStack();
        }

        formatNum = String.format("%6.7f", result).replace(',', ' ');
        System.out.println("End Result: " + formatNum);
    }

    private static void doOperation() {
        switch (operator) {
            case '+':
                pop();
                result = value2 + value1;
                push();
                break;
            case '-':
                pop();
                result = value2 - value1;
                push();
                break;
            case '*':
                pop();
                result = value2 * value1;
                push();
                break;
            case '/':
                pop();
                result = value2 / value1;
                push();
                break;
            case '^':
                pop();
                result = Math.pow(value2, value1);
                push();
                break;
            default:
                if (cInNum.matches("-?\\d+(\\.\\d+)?")) {
                    result = Double.parseDouble(cInNum);
                    push();
                }
                break;
        }
    }

    private static void showStack() {
        System.out.print("STACK: ");
        for (idx = 1; idx < sp; idx++) {
            formatNum = String.format("%6.7f", stack[idx]).replace(',', ' ');
            if (stack[idx] < 0) {
                System.out.print("    -" + formatNum.trim());
            } else {
                System.out.print(formatNum);
            }
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
}