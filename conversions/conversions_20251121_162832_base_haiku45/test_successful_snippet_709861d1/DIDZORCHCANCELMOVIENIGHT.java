import java.util.Calendar;
import java.util.Scanner;

public class DIDZORCHCANCELMOVIENIGHT {
    
    private static String TODAY;
    private static String USER_INPUT = " ";
    private static String INPUT_RESULT = " ";
    private static String ISMOVIENIGHT = "y";
    private static String ISCANCELLED = "y";
    
    public static void main(String[] args) {
        startHere();
    }
    
    private static void startHere() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        TODAY = String.valueOf(dayOfWeek);
        
        if (dayOfWeek == 7) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            receiveUserInput();
            if (!INPUT_RESULT.equals(" ") && !INPUT_RESULT.isEmpty()) {
                ISMOVIENIGHT = INPUT_RESULT;
            }
            
            if (ISMOVIENIGHT.equals("y")) {
                System.out.println("Was ist cancelled? (y/n)");
                receiveUserInput();
                if (!INPUT_RESULT.equals(" ") && !INPUT_RESULT.isEmpty()) {
                    ISCANCELLED = INPUT_RESULT;
                }
                
                if (ISCANCELLED.equals("y")) {
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
    }
    
    private static void receiveUserInput() {
        USER_INPUT = " ";
        INPUT_RESULT = " ";
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            USER_INPUT = scanner.nextLine();
        }
        USER_INPUT = USER_INPUT.toLowerCase();
        if (!USER_INPUT.equals("y")) {
            INPUT_RESULT = USER_INPUT;
        }
    }
}