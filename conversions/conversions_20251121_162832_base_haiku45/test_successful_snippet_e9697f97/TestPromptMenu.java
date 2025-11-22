import java.util.Scanner;

public class TestPromptMenu {
    public static void main(String[] args) {
        int numOptions = 4;
        String[] exampleOptions = {
            "fee fie",
            "huff and puff",
            "mirror mirror",
            "tick tock"
        };
        
        String[] chosenOption = new String[1];
        PromptMenu.promptMenu(numOptions, exampleOptions, chosenOption);
        
        System.out.println("You chose: " + chosenOption[0]);
    }
}

class PromptMenu {
    public static void promptMenu(int numOptions, String[] menuOptions, String[] chosenOption) {
        if (numOptions == 0) {
            chosenOption[0] = "";
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int userInput = 0;
        
        while (!validInput) {
            displayMenuOptions(numOptions, menuOptions);
            
            System.out.print("Choose an option: ");
            try {
                userInput = scanner.nextInt();
            } catch (Exception e) {
                scanner.nextLine();
                userInput = 0;
            }
            
            validInput = validateInput(userInput, numOptions);
        }
        
        chosenOption[0] = menuOptions[userInput - 1];
    }
    
    private static void displayMenuOptions(int numOptions, String[] menuOptions) {
        for (int optionsIndex = 1; optionsIndex <= numOptions; optionsIndex++) {
            System.out.println(optionsIndex + ". " + menuOptions[optionsIndex - 1]);
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