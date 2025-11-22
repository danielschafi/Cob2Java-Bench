```java
import java.util.Scanner;
import java.util.Stack;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class RPN {
    private String lineIn;
    private int ip = 0;
    private String cInNum;
    private Stack<BigDecimal> stack;
    private String operator;
    private BigDecimal value1;
    private BigDecimal value2;
    private BigDecimal result;
    private int idx;
    private DecimalFormat formatNum;

    public RPN() {
        stack = new Stack<>();
        formatNum = new DecimalFormat("0000000.0000000");
    }

    public static void main(String[] args) {
        RPN rpn = new RPN();
        rpn.mainProgram();
    }

    private void mainProgram() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the RPN Equation: ");
        lineIn = scanner.nextLine();
        scanner.close();

        String[] tokens = lineIn.split(" ");
        
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            
            operator = token;
            doOperation();
            showStack();
        }

        if (!stack.isEmpty()) {
            result = stack.peek();
            System.out.println("End Result: " + formatNum.format(result));
        }
    }

    private void doOperation() {
        if (isNumeric(operator)) {
            result = new BigDecimal(operator);
            push();
        } else {
            switch (operator) {
                case "+":
                    pop();
                    result = value2.add(value1);
                    push();
                    break;
                case "-":
                    pop();
                    result = value2.subtract(value1);
                    push();
                    break;
                case "*":
                    pop();
                    result = value2.multiply(value1);
                    push();
                    break;
                case "/":
                    pop();
                    result = value2.divide(value1, 7, java.math.RoundingMode.HALF_UP);
                    push();
                    break;
                case "^":
                    pop();
                    result = value2.pow(value1.intValue());
                    push();
                    break;
            }
        }
    }

    private void showStack() {
        System.out.print("STACK: ");
        for (int i = 0; i < stack.size(); i++) {
            BigDecimal val = stack.get(i);
            if (val.signum() < 0) {
                System.out.print("    -" + formatNum.format(val.abs()));
            } else {
                System.out.print(formatNum.format(val));
            }
        }
        System.out.println();
    }

    private void push() {
        stack.push(result);
    }

    private void pop() {
        value1 = stack.pop();
        value2 = stack.pop();
    }

    private boolean isNumeric(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}