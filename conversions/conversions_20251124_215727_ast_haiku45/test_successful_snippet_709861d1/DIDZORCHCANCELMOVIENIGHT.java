import java.util.Scanner;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class DIDZORCHCANCELMOVIENIGHT {
    private static int today = 0;
    private static String userInput = " ";
    private static String inputResult = " ";
    private static String isMovieNight = "y";
    private static String isCancelled = "y";

    public static void main(String[] args) {
        startHere();
    }

    private static void startHere() {
        today = LocalDate.now().getDayOfWeek().getValue();
        
        if (today == 6) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            receiveUserUserInput();
            if (!inputResult.equals(" ") && !inputResult.equals("\0")) {
                isMovieNight = inputResult;
            }

            if (isMovieNight.equals("y")) {
                System.out.println("Was ist cancelled? (y/n)");
                receiveUserUserInput();
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
    }

    private static void receiveUserUserInput() {
        userInput = " ";
        inputResult = " ";
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();
        if (!userInput.equals("y")) {
            inputResult = userInput;
        }
    }
}