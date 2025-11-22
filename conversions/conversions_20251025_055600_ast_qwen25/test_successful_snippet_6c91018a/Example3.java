import java.util.Scanner;

public class Example3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = 0;
        int grade = 0;
        char score = 'B';
        int canVoteFlag = 0;
        char testNumber;

        System.out.print("Enter Age : ");
        age = scanner.nextInt();

        if (age > 18) {
            System.out.println("You can vote");
        } else {
            System.out.println("You can't vote");
        }

        if (age < 5) {
            System.out.println("Stay Home");
        }
        if (age == 5) {
            System.out.println("Go to Kindergarten");
        }
        if (age > 5 && age < 18) {
            grade = age - 5;
            System.out.println("Go to Grade " + grade);
        }
        if (age >= 18) {
            System.out.println("Go to College");
        }

        if (isPassingScore(score)) {
            System.out.println("You Passed");
        } else {
            System.out.println("You Failed");
        }

        if (!Character.isDigit(score)) {
            System.out.println("Not a Number");
        }

        if (age > 18) {
            canVoteFlag = 1;
        } else {
            canVoteFlag = 0;
        }
        System.out.println("Vote " + canVoteFlag);

        System.out.print("Enter Single Number or X to Exit : ");
        testNumber = scanner.next().charAt(0);

        while (Character.isDigit(testNumber)) {
            if (isPrime(testNumber)) {
                System.out.println("Prime");
            } else if (isOdd(testNumber)) {
                System.out.println("Odd");
            } else if (isEven(testNumber)) {
                System.out.println("Even");
            } else if (isLessThan5(testNumber)) {
                System.out.println("Less than 5");
            } else {
                System.out.println("Default Action");
            }
            System.out.print("Enter Single Number or X to Exit : ");
            testNumber = scanner.next().charAt(0);
        }
    }

    private static boolean isPassingScore(char score) {
        return score == 'A' || score == 'B' || score == 'C' || score == 'D';
    }

    private static boolean isPrime(char testNumber) {
        return testNumber == '1' || testNumber == '3' || testNumber == '5' || testNumber == '7';
    }

    private static boolean isOdd(char testNumber) {
        return testNumber == '1' || testNumber == '3' || testNumber == '5' || testNumber == '7' || testNumber == '9';
    }

    private static boolean isEven(char testNumber) {
        return testNumber == '2' || testNumber == '4' || testNumber == '6' || testNumber == '8';
    }

    private static boolean isLessThan5(char testNumber) {
        return testNumber >= '1' && testNumber <= '4';
    }
}