import java.io.*;
import java.util.*;

public class USPRESRB {
    static class PresEntry {
        String presIndex;
        String presName;
        
        PresEntry() {
            this.presIndex = "";
            this.presName = "";
        }
    }
    
    static PresEntry[] wsPresTable = new PresEntry[45];
    static int wsPresNumber = 0;
    static int wsPresReadSwitch = 1;
    static int wsPresSub = 0;
    static String usrinput = "";
    static int listindex = 0;
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        try {
            driver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static void driver() throws IOException {
        System.out.println("USPRESRB BY RYAN BROOKS");
        
        initializePresTable();
        readPresFile();
        
        while (!usrinput.equalsIgnoreCase("X")) {
            selectPresident();
            
            if (wsPresNumber > 0 && wsPresNumber <= 45) {
                System.out.println(" ");
                System.out.println("You picked " + wsPresTable[wsPresNumber - 1].presIndex + 
                                 ", President " + wsPresTable[wsPresNumber - 1].presName);
                System.out.println(" ");
            }
            wsPresNumber = 0;
        }
    }
    
    static void initializePresTable() {
        for (int i = 0; i < 45; i++) {
            wsPresTable[i] = new PresEntry();
        }
    }
    
    static void readPresFile() throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("USPRES.DAT"));
            String line;
            int index = 0;
            
            while ((line = reader.readLine()) != null && index < 45) {
                if (line.length() >= 27) {
                    wsPresTable[index].presIndex = line.substring(0, 2);
                    wsPresTable[index].presName = line.substring(2, 27);
                } else if (line.length() > 2) {
                    wsPresTable[index].presIndex = line.substring(0, Math.min(2, line.length()));
                    wsPresTable[index].presName = line.substring(2);
                }
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File USPRES.DAT not found");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    static void selectPresident() {
        boolean validPres = false;
        
        while (!validPres) {
            System.out.println(" ");
            System.out.print("Which president (01-45)?  X exits, A lists all. ");
            
            usrinput = scanner.nextLine().trim();
            
            if (usrinput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (usrinput.equalsIgnoreCase("X")) {
                System.exit(0);
            } else if (isNumeric(usrinput)) {
                wsPresNumber = Integer.parseInt(usrinput);
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
    
    static void displayAll() {
        listindex = 0;
        while (listindex < 45) {
            System.out.println("President " + wsPresTable[listindex].presName);
            listindex++;
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