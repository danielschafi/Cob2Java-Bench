import java.util.Scanner;

public class TestPromptMenu {
    public static void main(String[] args) {
        int numOptions = 4;
        String[] exampleMenu = {"fee fie", "huff and puff", "mirror mirror", "tick tock"};
        String chosenOption = "";

        chosenOption = promptMenu(numOptions, exampleMenu);

        System.out.println("You chose: " + chosenOption);
    }

    public static String promptMenu(int numOptions, String[] menuOptions) {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        boolean validInput = false;
        String chosenOption = "";

        if (numOptions == 0) {
            return chosenOption;
        }

        while (!validInput) {
            displayMenuOptions(numOptions, menuOptions);

            System.out.print("Choose an option: ");
            userInput = scanner.nextInt();

            if (userInput <= 0 || userInput > numOptions) {
                System.out.println("Invalid input.");
            } else {
                validInput = true;
            }
        }

        chosenOption = menuOptions[userInput - 1];
        return chosenOption;
    }

    public static void displayMenuOptions(int numOptions, String[] optionsTable) {
        for (int optionsIndex = 1; optionsIndex <= numOptions; optionsIndex++) {
            System.out.println(optionsIndex + ". " + optionsTable[optionsIndex - 1]);
        }
    }
}