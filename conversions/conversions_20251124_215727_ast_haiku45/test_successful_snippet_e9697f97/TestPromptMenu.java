import java.util.Scanner;

public class TestPromptMenu {
    public static void main(String[] args) {
        int numOptions = 4;
        String[] exampleOptions = {"fee fie", "huff and puff", "mirror mirror", "tick tock"};
        String[] chosenOption = new String[1];
        chosenOption[0] = "";
        
        promptMenu(numOptions, exampleOptions, chosenOption);
        
        System.out.println("You chose: " + chosenOption[0]);
    }
    
    public static void promptMenu(int numOptions, String[] menuOptions, String[] chosenOption) {
        int userInput = 0;
        boolean validInput = false;
        int optionsIndex = 0;
        String indexDisplay = "";
        
        if (numOptions == 0) {
            chosenOption[0] = "";
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        
        while (!validInput) {
            displayMenuOptions(numOptions, menuOptions);
            
            System.out.print("Choose an option: ");
            userInput = scanner.nextInt();
            
            validateInput(userInput, numOptions);
            
            if (userInput > 0 && userInput <= numOptions) {
                validInput = true;
            }
        }
        
        chosenOption[0] = menuOptions[userInput - 1];
        
        scanner.close();
    }
    
    public static void displayMenuOptions(int numOptions, String[] menuOptions) {
        for (int optionsIndex = 1; optionsIndex <= numOptions; optionsIndex++) {
            String indexDisplay = String.format("%d", optionsIndex).trim();
            System.out.println(indexDisplay + ". " + menuOptions[optionsIndex - 1]);
        }
    }
    
    public static void validateInput(int userInput, int numOptions) {
        if (userInput == 0 || userInput > numOptions) {
            System.out.println("Invalid input.");
        }
    }
}