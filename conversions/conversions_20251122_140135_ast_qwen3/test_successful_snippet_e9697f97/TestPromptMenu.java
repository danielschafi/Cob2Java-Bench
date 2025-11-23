import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class TestPromptMenu {
    public static void main(String[] args) {
        int numOptions = 4;
        String[] exampleOptions = {"fee fie", "huff and puff", "mirror mirror", "tick tock"};
        String chosenOption = promptMenu(numOptions, exampleOptions);
        System.out.println("You chose: " + chosenOption);
    }

    public static String promptMenu(int numOptions, String[] menuOptions) {
        if (numOptions == 0) {
            return "";
        }

        int userInput;
        boolean validInput = false;

        do {
            displayMenuOptions(menuOptions);
            
            System.out.print("Choose an option: ");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                userInput = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                userInput = -1;
            }
            
            validInput = validateInput(userInput, numOptions);
        } while (!validInput);

        return menuOptions[userInput - 1];
    }

    private static void displayMenuOptions(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private static boolean validateInput(int userInput, int numOptions) {
        if (userInput == 0 || userInput > numOptions) {
            System.out.println("Invalid input.");
            return false;
        }
        return true;
    }
}