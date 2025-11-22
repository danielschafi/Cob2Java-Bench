import java.util.Scanner;

public class TestPromptMenu {
    private static final int NUM_OPTIONS = 4;
    private static final String[] EXAMPLE_OPTIONS = {
        "fee fie",
        "huff and puff",
        "mirror mirror",
        "tick tock"
    };
    private static String chosenOption;

    public static void main(String[] args) {
        promptMenu(NUM_OPTIONS, EXAMPLE_OPTIONS, chosenOption);
        System.out.println("You chose: " + chosenOption);
    }

    public static void promptMenu(int numOptions, String[] menuOptions, String chosenOption) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int userInput = 0;

        if (numOptions == 0) {
            chosenOption = "";
            return;
        }

        while (!validInput) {
            displayMenuOptions(numOptions, menuOptions);
            System.out.print("Choose an option: ");
            userInput = scanner.nextInt();

            if (userInput == 0 || userInput > numOptions) {
                System.out.println("Invalid input.");
            } else {
                validInput = true;
            }
        }

        chosenOption = menuOptions[userInput - 1];
    }

    public static void displayMenuOptions(int numOptions, String[] optionsTable) {
        for (int optionsIndex = 1; optionsIndex <= numOptions; optionsIndex++) {
            System.out.println(optionsIndex + ". " + optionsTable[optionsIndex - 1]);
        }
    }
}