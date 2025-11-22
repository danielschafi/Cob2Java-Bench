import java.util.Scanner;

public class example3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int Age = 0;
        int Grade = 0;
        String Score = "B";
        int CanVoteFlag = 0;
        boolean CanVote = false;
        boolean CantVote = true;
        String TestNumber;

        System.out.print("Enter Age : ");
        Age = scanner.nextInt();
        scanner.nextLine();  // Consume newline

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

        if (Score.matches("[A-C]|D")) {
            System.out.println("You Passed");
        } else {
            System.out.println("You Failed");
        }

        if (!Score.matches("\\d")) {
            System.out.println("Not a Number");
        }

        if (Age > 18) {
            CanVote = true;
            CantVote = false;
        } else {
            CanVote = false;
            CantVote = true;
        }
        System.out.println("Vote " + CanVoteFlag);

        System.out.print("Enter Single Number or X to Exit : ");
        TestNumber = scanner.nextLine();

        while (TestNumber.matches("[0-9]")) {
            if (TestNumber.equals("1") || TestNumber.equals("3") || TestNumber.equals("5") || TestNumber.equals("7")) {
                System.out.println("Prime");
            }
            if (TestNumber.equals("1") || TestNumber.equals("3") || TestNumber.equals("5") || TestNumber.equals("7") || TestNumber.equals("9")) {
                System.out.println("Odd");
            }
            if (TestNumber.equals("2") || TestNumber.equals("4") || TestNumber.equals("6") || TestNumber.equals("8")) {
                System.out.println("Even");
            }
            if (TestNumber.matches("[1-4]")) {
                System.out.println("Less than 5");
            }
            System.out.print("Enter Single Number or X to Exit : ");
            TestNumber = scanner.nextLine();
        }

        scanner.close();
    }
}