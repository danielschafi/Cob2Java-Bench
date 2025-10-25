import java.util.Scanner;

public class DIDZORCHCANCELMOVIENIGHT {
    public static void main(String[] args) {
        int TODAY = 0;
        String USER_INPUT = " ";
        String INPUT_RESULT = " ";
        String ISMOVIENIGHT = "y";
        String ISCANCELLED = "y";
        Scanner scanner = new Scanner(System.in);

        TODAY = getDayOfWeek();

        if (TODAY == 6) {
            System.out.println("It is saturday!");
            System.out.println("Should there be a movie night? (y/n)");
            receiveUserInput(scanner);
            if (!INPUT_RESULT.equals(" ") && !INPUT_RESULT.equals("\0")) {
                ISMOVIENIGHT = INPUT_RESULT;
            }

            if (ISMOVIENIGHT.equals("y")) {
                System.out.println("Was it cancelled? (y/n)");
                receiveUserInput(scanner);
                if (!INPUT_RESULT.equals(" ") && !INPUT_RESULT.equals("\0")) {
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

    private static void receiveUserInput(Scanner scanner) {
        String USER_INPUT = " ";
        String INPUT_RESULT = " ";
        USER_INPUT = scanner.nextLine().trim().toLowerCase();
        if (!USER_INPUT.equals("y")) {
            INPUT_RESULT = USER_INPUT;
        }
    }

    private static int getDayOfWeek() {
        // This method should return the current day of the week as an integer (1-7)
        // For demonstration purposes, we'll return 6 (Saturday)
        return 6;
    }
}