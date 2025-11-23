public class Chapt12g {
    private static String[][][][] kittenNames = new String[8][8][8][8];
    private static String[][] wifeNames = new String[8][8];
    private static String[][] sackColors = new String[8][8];
    private static boolean kittenFound = false;

    public static void main(String[] args) {
        kittenNames[1][3][2][6] = "Hershey";
        wifeNames[1][1] = "Darlene";
        sackColors[1][3] = "Yellow";

        for (int wifeIndex = 1; wifeIndex <= 7 && !kittenFound; wifeIndex++) {
            for (int sackIndex = 1; sackIndex <= 7 && !kittenFound; sackIndex++) {
                for (int catIndex = 1; catIndex <= 7 && !kittenFound; catIndex++) {
                    for (int kittenIndex = 1; kittenIndex <= 7; kittenIndex++) {
                        if (kittenNames[wifeIndex][sackIndex][catIndex][kittenIndex] != null &&
                            kittenNames[wifeIndex][sackIndex][catIndex][kittenIndex].equals("Hershey")) {
                            kittenFound = true;
                            break;
                        }
                    }
                }
            }
        }

        if (kittenFound) {
            System.out.println("Hershey found in the " + 
                sackColors[wifeNames.length > 1 ? 1 : 0][sackColors[1].length > 3 ? 3 : 0] + 
                " Sack, Being carried by " + 
                wifeNames[1][1]);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}