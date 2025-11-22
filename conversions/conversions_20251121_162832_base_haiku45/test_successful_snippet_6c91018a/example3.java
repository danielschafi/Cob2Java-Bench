import java.util.Scanner;

public class example3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int Age = 0;
        int Grade = 0;
        String Score = "B";
        int CanVoteFlag = 0;
        String TestNumber = "";
        
        System.out.print("Enter Age : ");
        Age = scanner.nextInt();
        
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
        scanner.nextLine();
        TestNumber = scanner.nextLine();
        
        while (isANumber(TestNumber)) {
            if (isPrime(TestNumber)) {
                System.out.println("Prime");
            } else if (isOdd(TestNumber)) {
                System.out.println("Odd");
            } else if (isEven(TestNumber)) {
                System.out.println("Even");
            } else if (isLessThan5(TestNumber)) {
                System.out.println("Less than 5");
            } else {
                System.out.println("Default Action");
            }
            TestNumber = scanner.nextLine();
        }
        
        scanner.close();
    }
    
    private static boolean isPassingScore(String score) {
        return score.equals("A") || score.equals("B") || score.equals("C") || score.equals("D");
    }
    
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isPrime(String num) {
        return num.equals("1") || num.equals("3") || num.equals("5") || num.equals("7");
    }
    
    private static boolean isOdd(String num) {
        return num.equals("1") || num.equals("3") || num.equals("5") || num.equals("7") || num.equals("9");
    }
    
    private static boolean isEven(String num) {
        return num.equals("2") || num.equals("4") || num.equals("6") || num.equals("8");
    }
    
    private static boolean isLessThan5(String num) {
        return num.equals("1") || num.equals("2") || num.equals("3") || num.equals("4");
    }
    
    private static boolean isANumber(String num) {
        if (num.length() != 1) {
            return false;
        }
        char c = num.charAt(0);
        return c >= '0' && c <= '9';
    }
}