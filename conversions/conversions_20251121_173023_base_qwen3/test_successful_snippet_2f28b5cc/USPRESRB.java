import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class USPRESRB {
    private static final String PRESIDENT_FILE = "USPRES.DAT";
    private static final int MAX_PRESIDENTS = 45;
    
    private static class PresidentRecord {
        String index;
        String name;
        
        PresidentRecord() {
            this.index = "";
            this.name = "";
        }
    }
    
    private static PresidentRecord[] presTable = new PresidentRecord[MAX_PRESIDENTS];
    private static String inputFileStatus = "";
    private static int presReadSwitch = 1;
    private static int presNumber = 0;
    private static int presSub = 0;
    private static String usrInput = "";
    private static int listIndex = 0;
    
    static {
        for (int i = 0; i < MAX_PRESIDENTS; i++) {
            presTable[i] = new PresidentRecord();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("USPRESRB BY RYAN BROOKS");
        
        try (BufferedReader reader = new BufferedReader(new FileReader(PRESIDENT_FILE))) {
            String line;
            presSub = 0;
            
            while ((line = reader.readLine()) != null && presSub < MAX_PRESIDENTS) {
                presSub++;
                if (line.length() >= 27) {
                    presTable[presSub - 1].index = line.substring(0, 2);
                    presTable[presSub - 1].name = line.substring(2, 27).trim();
                } else {
                    presTable[presSub - 1].index = line.substring(0, Math.min(2, line.length()));
                    presTable[presSub - 1].name = line.substring(2, line.length()).trim();
                }
            }
            
            presReadSwitch = 0;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        
        while (!usrInput.equalsIgnoreCase("X")) {
            selectPresident();
            
            if (!usrInput.equalsIgnoreCase("X") && !usrInput.equalsIgnoreCase("A")) {
                System.out.println();
                System.out.println("You picked " + presTable[presNumber - 1].index + 
                                 ", President " + presTable[presNumber - 1].name);
                System.out.println();
                presNumber = 0;
            }
        }
    }
    
    private static void selectPresident() {
        boolean validPres = false;
        
        while (!validPres) {
            System.out.println();
            System.out.print("Which president (01-45)?  X exits, A lists all. ");
            
            try {
                BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
                usrInput = br.readLine();
            } catch (IOException e) {
                System.err.println("Error reading input: " + e.getMessage());
                return;
            }
            
            if (usrInput.equalsIgnoreCase("A")) {
                displayAll();
            } else if (usrInput.equalsIgnoreCase("X")) {
                return;
            } else if (isNumeric(usrInput)) {
                presNumber = Integer.parseInt(usrInput);
                if (presNumber >= 1 && presNumber <= 45) {
                    validPres = true;
                } else {
                    System.out.println();
                    System.out.println("Please enter a valid input");
                }
            } else {
                System.out.println();
                System.out.println("Please enter a valid input");
            }
        }
    }
    
    private static void displayAll() {
        listIndex = 0;
        while (listIndex < MAX_PRESIDENTS) {
            System.out.println("President " + presTable[listIndex].name);
            listIndex++;
        }
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
}