import java.util.Scanner;

public class RPN {
    private static final int MAX_STACK_SIZE = 50;
    private static double[] stack = new double[MAX_STACK_SIZE];
    private static int sp = 0;
    private static double value1;
    private static double value2;
    private static double result;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the RPN Equation: ");
        String lineIn = scanner.nextLine();
        scanner.close();

        String[] tokens = lineIn.trim().split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            doOperation(token);
            showStack();
        }

        System.out.println("End Result: " + formatNumber(stack[sp - 1]));
    }

    private static void doOperation(String operator) {
        if (isNumeric(operator)) {
            result = Double.parseDouble(operator);
            push();
        } else {
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
            }
        }
    }

    private static void showStack() {
        System.out.print("STACK: ");
        for (int idx = 0; idx < sp; idx++) {
            String formatted = formatNumber(stack[idx]);
            if (stack[idx] < 0) {
                System.out.print("    -" + formatted.trim());
            } else {
                System.out.print(formatted);
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

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String formatNumber(double num) {
        double absNum = Math.abs(num);
        return String.format("%14.7f", absNum);
    }
}