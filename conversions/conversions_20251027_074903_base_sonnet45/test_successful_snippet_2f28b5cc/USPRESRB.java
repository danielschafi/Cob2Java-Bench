import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class USPRESRB {
    
    private static class PresEntry {
        String presIndex;
        String presName;
        
        public PresEntry() {
            this.presIndex = "  ";
            this.presName = "                         ";
        }
    }
    
    private PresEntry[] wsPresTable;
    private int wsPresNumber;
    private boolean endOfPres;
    private String userInput;
    
    public USPRESRB() {
        wsPresTable = new PresEntry[45];
        for (int i = 0; i < 45; i++) {
            wsPresTable[i] = new PresEntry();
        }
        wsPresNumber = 0;
        endOfPres = false;
        userInput = "";
    }
    
    public void run() {
        System.out.println("USPRESRB BY RYAN BROOKS");
        
        loadPresidentFile();
        
        Scanner scanner = new Scanner(System.in);
        
        while (!userInput.equals("X")) {
            selectPresident(scanner);
            
            if (!userInput.equals("X") && wsPresNumber >= 1 && wsPresNumber <= 45) {
                System.out.println(" ");
                System.out.println("You picked " + wsPresTable[wsPresNumber - 1].presIndex + 
                                 ", President " + wsPresTable[wsPresNumber - 1].presName);
                System.out.println(" ");
                wsPresNumber = 0;
            }
        }
        
        scanner.close();
    }
    
    private void loadPresidentFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("USPRES.DAT"))) {
            int index = 0;
            String line;
            
            while ((line = reader.readLine()) != null && index < 45) {
                if (line.length() >= 27) {
                    wsPresTable[index].presIndex = line.substring(0, 2);
                    wsPresTable[index].presName = line.substring(2, 27);
                } else if (line.length() >= 2) {
                    wsPresTable[index].presIndex = line.substring(0, 2);
                    if (line.length() > 2) {
                        wsPresTable[index].presName = line.substring(2);
                    }
                }
                index++;
            }
            endOfPres = true;
        } catch (IOException e) {
            endOfPres = true;
        }
    }
    
    private void selectPresident(Scanner scanner) {
        boolean validPres = false;
        
        while (!validPres) {
            System.out.println(" ");
            System.out.println("Which president (01-45)?  X exits, A lists all. ");
            
            userInput = scanner.nextLine().trim();
            
            if (userInput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (userInput.equalsIgnoreCase("X")) {
                return;
            } else if (isNumeric(userInput)) {
                wsPresNumber = Integer.parseInt(userInput);
                if (wsPresNumber >= 1 && wsPresNumber <= 45) {
                    validPres = true;
                } else {
                    System.out.println(" ");
                    System.out.println("Please enter a valid input");
                }
            } else {
                System.out.println(" ");
                System.out.println("Please enter a valid input");
            }
        }
    }
    
    private void displayAll() {
        for (int listIndex = 0; listIndex < 45; listIndex++) {
            System.out.println("President " + wsPresTable[listIndex].presName);
        }
    }
    
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        USPRESRB program = new USPRESRB();
        program.run();
    }
}