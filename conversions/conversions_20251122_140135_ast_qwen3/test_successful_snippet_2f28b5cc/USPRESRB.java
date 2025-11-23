import java.io.*;
import java.util.*;

public class USPRESRB {
    private static final String FILE_NAME = "USPRES.DAT";
    private static final int TABLE_SIZE = 45;
    
    private static class President {
        String index;
        String name;
        
        President() {
            this.index = "";
            this.name = "";
        }
    }
    
    private static President[] wsPresTable = new President[TABLE_SIZE];
    private static Scanner scanner = new Scanner(System.in);
    private static int wsPresNumber = 0;
    private static int wsPresSub = 1;
    private static boolean endOfPres = false;
    private static String usrinput = "";
    private static int listindex = 0;
    
    static {
        for (int i = 0; i < TABLE_SIZE; i++) {
            wsPresTable[i] = new President();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("USPRESRB BY RYAN BROOKS");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null && !endOfPres) {
                if (wsPresSub <= TABLE_SIZE) {
                    wsPresTable[wsPresSub - 1].index = line.substring(0, Math.min(2, line.length()));
                    wsPresTable[wsPresSub - 1].name = line.substring(2, Math.min(27, line.length()));
                    wsPresSub++;
                } else {
                    endOfPres = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
        
        while (!usrinput.equalsIgnoreCase("X")) {
            selectPresident();
            
            if (!usrinput.equalsIgnoreCase("X") && !usrinput.equalsIgnoreCase("A")) {
                System.out.println();
                System.out.println("You picked " + wsPresTable[wsPresNumber - 1].index + 
                                 ", President " + wsPresTable[wsPresNumber - 1].name);
                System.out.println();
                wsPresNumber = 0;
            }
        }
    }
    
    private static void selectPresident() {
        while (!isValidPres(wsPresNumber)) {
            System.out.println();
            System.out.println("Which president (01-45)?  X exits, A lists all. ");
            
            usrinput = scanner.nextLine().trim();
            
            if (usrinput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (usrinput.equalsIgnoreCase("X")) {
                return;
            } else if (isNumeric(usrinput)) {
                wsPresNumber = Integer.parseInt(usrinput);
                if (!isValidPres(wsPresNumber)) {
                    System.out.println();
                    System.out.println("Please enter a valid input");
                }
            } else {
                System.out.println();
                System.out.println("Please enter a valid input");
            }
        }
    }
    
    private static boolean isValidPres(int number) {
        return number >= 1 && number <= 45;
    }
    
    private static boolean isNumeric(String str) {
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
    
    private static void displayAll() {
        listindex = 0;
        while (listindex < 45) {
            System.out.println("President " + wsPresTable[listindex].name);
            listindex++;
        }
    }
}