import java.util.Scanner;

public class NthRoot {
    private double root;
    private double num;
    private double precision;
    private double temp0;
    private double temp1;
    private String field1;
    private String field2;
    private String field3;
    private static final String HEADER = "   Number           Root           Precision.";
    private String dispRoot;
    private Scanner scanner;

    public NthRoot() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        NthRoot program = new NthRoot();
        program.mainProgram();
    }

    private void mainProgram() {
        while (true) {
            getInput();
            if (precision == 0.0) {
                precision = 0.001;
            }
            computeRoot();
            field2 = formatNumber((int) root, "ZZZZ9");
            field1 = formatNumber(num, "ZZZZZ.ZZZZZ");
            field3 = formatNumber(precision, "9.999999999");
            System.out.println(HEADER);
            System.out.println(field1 + "     " + field2 + "              " + field3);
            System.out.println(" ");
            dispRoot = formatNumber(temp1, "ZZZZZ.ZZZZZ");
            System.out.println("The Root is: " + dispRoot);
        }
    }

    private void getInput() {
        System.out.print("Input Base Number: ");
        num = scanner.nextDouble();
        if (num == 0) {
            System.out.println("Good Bye.");
            System.exit(0);
        }
        System.out.print("Input Root: ");
        root = scanner.nextDouble();
        System.out.print("Input Desired Precision: ");
        precision = scanner.nextDouble();
    }

    private void computeRoot() {
        temp0 = root;
        temp1 = num / root;
        while (Math.abs(temp0 - temp1) >= precision) {
            temp0 = temp1;
            temp1 = ((root - 1.0) * temp1 + num / Math.pow(temp1, root - 1.0)) / root;
        }
    }

    private String formatNumber(double value, String format) {
        if (format.contains(".")) {
            return String.format("%.5f", value);
        }
        return String.format("%.0f", value);
    }

    private String formatNumber(int value, String format) {
        return String.format("%d", value);
    }
}