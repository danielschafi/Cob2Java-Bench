import java.util.Scanner;

public class TestPromptMenu {
    public static void main(String[] args) {
        int numOptions = 4;
        String[] exampleMenu = {"fee fie", "huff and puff", "mirror mirror", "tick tock"};
        String chosenOption = new String(new char[30]).replace('\0', ' ');

        promptMenu(numOptions, exampleMenu, chosenOption);

        System.out.println("You chose: " + chosenOption.trim());
    }

    public static void promptMenu(int numOptions, String[] menuOptions, String chosenOption) {
        if (numOptions == 0) {
            chosenOption = new String(new char[30]).replace('\0', ' ');
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int userInput;
        int optionsIndex;
        String indexDisplay;

        while (!validInput) {
            for (optionsIndex = 1; optionsIndex <= numOptions; optionsIndex++) {
                indexDisplay = String.format("%10d", optionsIndex).trim();
                System.out.println(indexDisplay + ". " + menuOptions[optionsIndex - 1]);
            }

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
}