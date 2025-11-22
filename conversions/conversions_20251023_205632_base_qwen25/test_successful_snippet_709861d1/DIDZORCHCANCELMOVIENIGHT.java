import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class DIDZORCHCANCELMOVIENIGHT {
    public static void main(String[] args) {
        int TODAY = 0;
        String USER_INPUT = " ";
        String INPUT_RESULT = " ";
        String ISMOVIENIGHT = "y";
        String ISCANCELLED = "y";

        TODAY = LocalDate.now().getDayOfWeek().getValue();

        if (TODAY == 6) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            INPUT_RESULT = receiveUserInput();
            if (!INPUT_RESULT.equals(" ") && !INPUT_RESULT.equals("\u0000")) {
                ISMOVIENIGHT = INPUT_RESULT;
            }

            if (ISMOVIENIGHT.equals("y")) {
                System.out.println("Was it cancelled? (y/n)");
                INPUT_RESULT = receiveUserInput();
                if (!INPUT_RESULT.equals(" ") && !INPUT_RESULT.equals("\u0000")) {
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

    public static String receiveUserInput() {
        Scanner scanner = new Scanner(System.in);
        String USER_INPUT = " ";
        String INPUT_RESULT = " ";
        USER_INPUT = scanner.nextLine().toLowerCase();
        if (!USER_INPUT.equals("y")) {
            INPUT_RESULT = USER_INPUT;
        }
        return INPUT_RESULT;
    }
}