import java.util.Scanner;

public class example3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int Age = 0;
        int Grade = 0;
        String Score = "B";
        int CanVoteFlag = 0;
        
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
        
        System.out.print("Enter Single Number or X to Exit : ");
        String TestNumber = scanner.next();
        
        while (isANumber(TestNumber)) {
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
            
            System.out.print("Enter Single Number or X to Exit : ");
            TestNumber = scanner.next();
        }
        
        scanner.close();
    }
    
    private static boolean isPassingScore(String score) {
        return score.equals("A") || score.equals("B") || score.equals("C") || score.equals("D");
    }
    
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private static boolean isANumber(String str) {
        return str.matches("[0-9]");
    }
    
    private static boolean isPrime(String str) {
        return str.equals("1") || str.equals("3") || str.equals("5") || str.equals("7");
    }
    
    private static boolean isOdd(String str) {
        return str.equals("1") || str.equals("3") || str.equals("5") || str.equals("7") || str.equals("9");
    }
    
    private static boolean isEven(String str) {
        return str.equals("2") || str.equals("4") || str.equals("6") || str.equals("8");
    }
    
    private static boolean lessThan5(String str) {
        return str.equals("1") || str.equals("2") || str.equals("3") || str.equals("4");
    }
}