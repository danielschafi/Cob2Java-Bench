import java.util.Arrays;

public class Chapt12g {
    private static final int WIFE_COUNT = 7;
    private static final int SACK_COUNT = 7;
    private static final int CAT_COUNT = 7;
    private static final int KITTEN_COUNT = 7;
    
    private static String[][][] wifeNames = new String[WIFE_COUNT][1];
    private static String[][][] sackColors = new String[WIFE_COUNT][SACK_COUNT][1];
    private static String[][][][] kittenNames = new String[WIFE_COUNT][SACK_COUNT][CAT_COUNT][KITTEN_COUNT];
    
    private static boolean kittenFound = false;
    
    public static void main(String[] args) {
        // Initialize arrays with default values
        for (int i = 0; i < WIFE_COUNT; i++) {
            wifeNames[i][0] = "";
            for (int j = 0; j < SACK_COUNT; j++) {
                sackColors[i][j][0] = "";
                for (int k = 0; k < CAT_COUNT; k++) {
                    for (int l = 0; l < KITTEN_COUNT; l++) {
                        kittenNames[i][j][k][l] = "";
                    }
                }
            }
        }
        
        // Set initial values as per COBOL code
        kittenNames[0][2][1][5] = "Hershey";
        wifeNames[0][0] = "Darlene";
        sackColors[0][2][0] = "Yellow";
        
        // Loop through all combinations
        for (int wifeIndex = 0; wifeIndex < WIFE_COUNT && !kittenFound; wifeIndex++) {
            for (int sackIndex = 0; sackIndex < SACK_COUNT && !kittenFound; sackIndex++) {
                for (int catIndex = 0; catIndex < CAT_COUNT && !kittenFound; catIndex++) {
                    // Search for Hershey in current combination
                    for (int kittenIndex = 0; kittenIndex < KITTEN_COUNT; kittenIndex++) {
                        if ("Hershey".equals(kittenNames[wifeIndex][sackIndex][catIndex][kittenIndex])) {
                            kittenFound = true;
                            break;
                        }
                    }
                }
            }
        }
        
        if (kittenFound) {
            System.out.println("Hershey found in the " + 
                sackColors[wifeNames.length > 0 ? 0 : 0][2][0] + 
                " Sack, Being carried by " + 
                wifeNames[0][0]);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}