import java.util.Scanner;

public class example3 {
    private static int Age = 0;
    private static int Grade = 0;
    private static String Score = "B";
    private static int CanVoteFlag = 0;
    private static String TestNumber = "";

    private static boolean isPassingScore(String value) {
        if (value.length() != 1) return false;
        char c = value.charAt(0);
        return (c >= 'A' && c <= 'C') || c == 'D';
    }

    private static boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) return false;
        for (char c : value.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private static boolean isPrime(String value) {
        return value.equals("1") || value.equals("3") || value.equals("5") || value.equals("7");
    }

    private static boolean isOdd(String value) {
        return value.equals("1") || value.equals("3") || value.equals("5") || value.equals("7") || value.equals("9");
    }

    private static boolean isEven(String value) {
        return value.equals("2") || value.equals("4") || value.equals("6") || value.equals("8");
    }

    private static boolean lessThan5(String value) {
        return value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4");
    }

    private static boolean aNumber(String value) {
        return value.length() == 1 && value.charAt(0) >= '0' && value.charAt(0) <= '9';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Age : ");
        Age = scanner.nextInt();
        scanner.nextLine();

        if (Age > 18) {
            System.out.println("You can vote");
        } else {
            System.out.println("You can't vote");
        }

        if (Age < 5) {
            System.out.println("Stay Home");
        }
        if (Age == 5) {
            System.out.println("Go to Kindergarten");
        }
        if (Age > 5 && Age < 18) {
            Grade = Age - 5;
            System.out.println("Go to Grade " + Grade);
        }
        if (Age >= 18) {
            System.out.println("Go to College");
        }

        if (isPassingScore(Score)) {
            System.out.println("You Passed");
        } else {
            System.out.println("You Failed");
        }

        if (!isNumeric(Score)) {
            System.out.println("Not a Number");
        }

        if (Age > 18) {
            CanVoteFlag = 1;
        } else {
            CanVoteFlag = 0;
        }
        System.out.println("Vote " + CanVoteFlag);

        System.out.println("Enter Singe Number or X to Exit : ");
        TestNumber = scanner.nextLine();

        while (aNumber(TestNumber)) {
            if (isPrime(TestNumber)) {
                System.out.println("Prime");
            } else if (isOdd(TestNumber)) {
                System.out.println("Odd");
            } else if (isEven(TestNumber)) {
                System.out.println("Even");
            } else if (lessThan5(TestNumber)) {
                System.out.println("Less than 5");
            } else {
                System.out.println("Default Action");
            }
            TestNumber = scanner.nextLine();
        }

        scanner.close();
    }
}