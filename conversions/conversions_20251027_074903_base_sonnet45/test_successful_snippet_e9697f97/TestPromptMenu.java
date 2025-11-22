import java.util.Scanner;

public class TestPromptMenu {
    
    public static void main(String[] args) {
        int numOptions = 4;
        
        String[] exampleOptions = new String[4];
        exampleOptions[0] = "fee fie";
        exampleOptions[1] = "huff and puff";
        exampleOptions[2] = "mirror mirror";
        exampleOptions[3] = "tick tock";
        
        String chosenOption = PromptMenu.promptMenu(numOptions, exampleOptions);
        
        System.out.println("You chose: " + chosenOption);
    }
}

class PromptMenu {
    
    public static String promptMenu(int numOptions, String[] menuOptions) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        boolean validInput = false;
        
        if (numOptions == 0) {
            return "";
        }
        
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
        
        return menuOptions[userInput - 1];
    }
    
    private static void displayMenuOptions(int numOptions, String[] menuOptions) {
        for (int optionsIndex = 1; optionsIndex <= numOptions; optionsIndex++) {
            System.out.println(String.format("%10d", optionsIndex) + ". " + menuOptions[optionsIndex - 1]);
        }
    }
    
    private static boolean validateInput(int userInput, int numOptions) {
        if (userInput == 0 || userInput > numOptions) {
            System.out.println("Invalid input.");
            return false;
        } else {
            return true;
        }
    }
}