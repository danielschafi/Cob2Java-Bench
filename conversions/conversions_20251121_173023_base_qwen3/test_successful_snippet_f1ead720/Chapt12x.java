public class Chapt12x {
    private static final String[] SACK_COLORS = {"Blue", "Red", "Yellow", "Green", "Purple", "Black", "White"};
    private static final String[][][][] WIFE_SACKS = new String[7][7][7][7];
    private static final String[] WIFE_NAMES = new String[7];
    private static boolean kittenFound = false;

    public static void main(String[] args) {
        // Initialize sacks with colors
        for (int wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (int sackColorIndex = 0; sackColorIndex < 7; sackColorIndex++) {
                int sackIndex = sackColorIndex;
                WIFE_SACKS[wifeIndex][sackIndex][0][0] = SACK_COLORS[sackColorIndex];
            }
        }

        // Setup the rest of the search
        WIFE_SACKS[0][2][1][5] = "Hershey"; // Kitten-Name (1, 3, 2, 6)
        WIFE_NAMES[0] = "Darlene";

        // Search for Hershey
        outerLoop:
        for (int wifeIndex = 0; wifeIndex < 7 && !kittenFound; wifeIndex++) {
            for (int sackIndex = 0; sackIndex < 7 && !kittenFound; sackIndex++) {
                for (int catIndex = 0; catIndex < 7 && !kittenFound; catIndex++) {
                    for (int kittenIndex = 0; kittenIndex < 7 && !kittenFound; kittenIndex++) {
                        if ("Hershey".equals(WIFE_SACKS[wifeIndex][sackIndex][catIndex][kittenIndex])) {
                            kittenFound = true;
                            break outerLoop;
                        }
                    }
                }
            }
        }

        if (kittenFound) {
            System.out.println("Hershey found in the " + 
                WIFE_SACKS[0][2][0][0] + 
                " Sack, Being carried by " + 
                WIFE_NAMES[0]);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}