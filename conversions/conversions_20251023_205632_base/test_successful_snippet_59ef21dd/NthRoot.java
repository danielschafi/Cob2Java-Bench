import java.util.Scanner;

public class NthRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double root, num, precision, temp0, temp1;

        String header = "   Number           Root           Precision.";

        while (true) {
            System.out.print("Input Base Number: ");
            num = scanner.nextDouble();
            if (num == 0) {
                System.out.println("Good Bye.");
                break;
            }
            System.out.print("Input Root: ");
            root = scanner.nextDouble();
            System.out.print("Input Desired Precision: ");
            precision = scanner.nextDouble();
            if (precision == 0.0) {
                precision = 0.001;
            }

            temp0 = root;
            temp1 = num / root;

            while (Math.abs(temp0 - temp1) >= precision) {
                temp0 = temp1;
                temp1 = ((root - 1.0) * temp1 + num / Math.pow(temp1, root - 1.0)) / root;
            }

            System.out.printf(header + "%n");
            System.out.printf("%10.5f %10.5f %10.9f%n", num, root, precision);
            System.out.println();
            System.out.printf("The Root is: %10.5f%n", temp1);
        }
        scanner.close();
    }
}