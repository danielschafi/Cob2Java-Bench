import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class DIDZORCHCANCELMOVIENIGHT {
    public static void main(String[] args) {
        int today = 0;
        String userInput = " ";
        String inputResult = " ";
        String isMovieNight = "y";
        String isCancelled = "y";
        
        Scanner scanner = new Scanner(System.in);
        
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        today = dayOfWeek.getValue();
        
        if (today == 6) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            inputResult = receiveUserInput(scanner);
            if (!inputResult.equals(" ") && !inputResult.equals("\0")) {
                isMovieNight = inputResult;
            }
            
            if (isMovieNight.equals("y")) {
                System.out.println("Was ist cancelled? (y/n)");
                inputResult = receiveUserInput(scanner);
                if (!inputResult.equals(" ") && !inputResult.equals("\0")) {
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
        
        scanner.close();
    }
    
    private static String receiveUserInput(Scanner scanner) {
        String userInput = " ";
        String inputResult = " ";
        
        if (scanner.hasNextLine()) {
            userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                userInput = " ";
            } else {
                userInput = userInput.substring(0, 1).toLowerCase();
            }
        }
        
        if (!userInput.equals("y")) {
            inputResult = userInput;
        }
        
        return inputResult;
    }
}