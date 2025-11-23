import java.util.Scanner;

public class DIDZORCHCANCELMOVIENIGHT {
    private static int TODAY;
    private static char USER_INPUT;
    private static char INPUT_RESULT;
    private static char ISMOVIENIGHT = 'y';
    private static char ISCANCELLED = 'y';
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Simulate ACCEPT TODAY FROM DAY-OF-WEEK
        // In a real implementation, you would get the actual day of week
        // For this example, we'll assume Saturday (6) as per the logic
        TODAY = 6; // Assuming it's Saturday
        
        if (TODAY == 6) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            
            RECEIVE_USER_USER_INPUT(scanner);
            
            if (INPUT_RESULT != ' ' && INPUT_RESULT != '\0') {
                ISMOVIENIGHT = INPUT_RESULT;
            }
            
            if (ISMOVIENIGHT == 'y') {
                System.out.println("Was ist cancelled? (y/n)");
                
                RECEIVE_USER_USER_INPUT(scanner);
                
                if (INPUT_RESULT != ' ' && INPUT_RESULT != '\0') {
                    ISCANCELLED = INPUT_RESULT;
                }
                
                if (ISCANCELLED == 'y') {
                    System.out.println("NOT AGAIN, ZORCH! >:(");
                } else {
                    System.out.println("See ya at movie night! :)");
                }
            } else {
                System.out.println("Then there is no movie night!");
                System.out.println("Try again next saturday!");
            }
        } else {
            System.out.println("It is not saturday!");
            System.out.println("Try again tomorrow!");
        }
        
        scanner.close();
    }
    
    public static void RECEIVE_USER_USER_INPUT(Scanner scanner) {
        USER_INPUT = ' ';
        INPUT_RESULT = ' ';
        
        String input = scanner.nextLine();
        if (input.length() > 0) {
            USER_INPUT = Character.toLowerCase(input.charAt(0));
        }
        
        if (USER_INPUT != 'y') {
            INPUT_RESULT = USER_INPUT;
        }
    }
}