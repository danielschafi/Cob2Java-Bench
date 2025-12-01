import java.io.*;
import java.util.Scanner;

public class USPRESRB {
    static class PresEntry {
        String index;
        String name;
        
        PresEntry() {
            index = "";
            name = "";
        }
    }
    
    static PresEntry[] wsPresTable = new PresEntry[45];
    static int wsPresNumber = 0;
    static int wsPresReadSwitch = 1;
    static int wsPresSub = 0;
    static String usrInput = "";
    static int listIndex = 0;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        for (int i = 0; i < 45; i++) {
            wsPresTable[i] = new PresEntry();
        }
        
        driver();
    }
    
    static void driver() {
        System.out.println("USPRESRB BY RYAN BROOKS");
        
        readPresFile();
        
        while (!usrInput.equals("X")) {
            selectPresident();
            
            System.out.println(" ");
            System.out.println("You picked " + wsPresTable[wsPresNumber - 1].index + 
                             ", President " + wsPresTable[wsPresNumber - 1].name);
            System.out.println(" ");
            wsPresNumber = 0;
        }
    }
    
    static void readPresFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("USPRES.DAT"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < 45) {
                if (line.length() >= 27) {
                    wsPresTable[index].index = line.substring(0, 2);
                    wsPresTable[index].name = line.substring(2, 27);
                } else {
                    wsPresTable[index].index = line.length() > 2 ? line.substring(0, 2) : line;
                    wsPresTable[index].name = line.length() > 2 ? line.substring(2) : "";
                }
                index++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
    
    static void selectPresident() {
        while (wsPresNumber < 1 || wsPresNumber > 45) {
            System.out.println(" ");
            System.out.print("Which president (01-45)?  X exits, A lists all. ");
            usrInput = scanner.nextLine().trim();
            
            if (usrInput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (usrInput.equalsIgnoreCase("X")) {
                System.exit(0);
            } else if (isNumeric(usrInput)) {
                wsPresNumber = Integer.parseInt(usrInput);
                if (wsPresNumber < 1 || wsPresNumber > 45) {
                    System.out.println(" ");
                    System.out.println("Please enter a valid input");
                }
            } else {
                System.out.println(" ");
                System.out.println("Please enter a valid input");
            }
        }
    }
    
    static void displayAll() {
        listIndex = 0;
        while (listIndex < 45) {
            System.out.println("President " + wsPresTable[listIndex].name);
            listIndex++;
        }
    }
    
    static boolean isNumeric(String str) {
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
}