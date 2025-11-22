import java.util.Scanner;

public class DIDZORCHCANCELMOVIENIGHT {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        int today = java.time.DayOfWeek.now().getValue();
        String userInput = "";
        String inputResult = "";
        String isMovieNight = "y";
        String isCancelled = "y";
        
        if (today == 6) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            receiveUserInput();
            if (!inputResult.equals("") && !inputResult.equals("\0")) {
                isMovieNight = inputResult;
            }
            
            if (isMovieNight.equals("y")) {
                System.out.println("Was ist cancelled? (y/n)");
                receiveUserInput();
                if (!inputResult.equals("") && !inputResult.equals("\0")) {
                    isCancelled = inputResult;
                }
                
                if (isCancelled.equals("y")) {
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
    
    public static void receiveUserInput() {
        userInput = scanner.nextLine();
        inputResult = "";
        userInput = userInput.toLowerCase();
        if (!userInput.equals("y")) {
            inputResult = userInput;
        }
    }
}