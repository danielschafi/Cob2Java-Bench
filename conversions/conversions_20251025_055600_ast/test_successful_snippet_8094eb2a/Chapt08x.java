import java.util.Scanner;

public class Chapt08x {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int highNumber = 0;
        int lowNumber = 0;

        System.out.print("Enter Number 1: ");
        number1 = scanner.nextInt();
        System.out.print("Enter Number 2: ");
        number2 = scanner.nextInt();
        System.out.print("Enter Number 3: ");
        number3 = scanner.nextInt();
        System.out.print("Enter Number 4: ");
        number4 = scanner.nextInt();

        highNumber = number1;
        lowNumber = number1;

        if (number2 < lowNumber) {
            lowNumber = number2;
        }
        if (number3 < lowNumber) {
            lowNumber = number3;
        }
        if (number4 < lowNumber) {
            lowNumber = number4;
        }
        if (number2 > highNumber) {
            highNumber = number2;
        }
        if (number3 > highNumber) {
            highNumber = number3;
        }
        if (number4 > highNumber) {
            highNumber = number4;
        }

        System.out.println(" Low Number is: " + lowNumber);
        System.out.println("High Number is: " + highNumber);

        highNumber = 0;
        lowNumber = 0;

        if (number1 <= number2 && number1 <= number3 && number1 <= number4) {
            lowNumber = number1;
        } else if (number2 <= number1 && number2 <= number3 && number2 <= number4) {
            lowNumber = number2;
        } else if (number3 <= number1 && number3 <= number2 && number3 <= number4) {
            lowNumber = number3;
        } else {
            lowNumber = number4;
        }

        if (number1 >= number2 && number1 >= number3 && number1 >= number4) {
            highNumber = number1;
        } else if (number2 >= number1 && number2 >= number3 && number2 >= number4) {
            highNumber = number2;
        } else if (number3 >= number1 && number3 >= number2 && number3 >= number4) {
            highNumber = number3;
        } else {
            highNumber = number4;
        }

        System.out.println(" Low Number is: " + lowNumber);
        System.out.println("High Number is: " + highNumber);

        scanner.close();
    }
}