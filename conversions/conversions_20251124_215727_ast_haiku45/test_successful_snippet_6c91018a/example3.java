import java.util.Scanner;

public class example3 {
    private static int age = 0;
    private static int grade = 0;
    private static String score = "B";
    private static int canVoteFlag = 0;
    private static String testNumber = "";
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.print("Enter Age : ");
        age = scanner.nextInt();
        scanner.nextLine();
        
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
        
        if (!isNumeric(score)) {
            System.out.println("Not a Number");
        }
        
        if (age > 18) {
            canVoteFlag = 1;
        } else {
            canVoteFlag = 0;
        }
        
        System.out.println("Vote " + canVoteFlag);
        
        System.out.println("Enter Singe Number or X to Exit : ");
        testNumber = scanner.nextLine();
        
        while (isANumber(testNumber)) {
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
            testNumber = scanner.nextLine();
        }
        
        System.exit(0);
    }
    
    private static boolean isPassingScore(String s) {
        return s.equals("A") || s.equals("B") || s.equals("C") || s.equals("D");
    }
    
    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isPrime(String s) {
        return s.equals("1") || s.equals("3") || s.equals("5") || s.equals("7");
    }
    
    private static boolean isOdd(String s) {
        return s.equals("1") || s.equals("3") || s.equals("5") || s.equals("7") || s.equals("9");
    }
    
    private static boolean isEven(String s) {
        return s.equals("2") || s.equals("4") || s.equals("6") || s.equals("8");
    }
    
    private static boolean isLessThan5(String s) {
        return s.equals("1") || s.equals("2") || s.equals("3") || s.equals("4");
    }
    
    private static boolean isANumber(String s) {
        return s.length() == 1 && s.compareTo("0") >= 0 && s.compareTo("9") <= 0;
    }
}