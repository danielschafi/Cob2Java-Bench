public class Chapt12x {
    private static final String[] SACK_COLORS = {"Blue", "Red", "Yellow", "Green", "Purple", "Black", "White"};
    private static final String[][][][] MAN_ON_THE_ROAD = new String[7][7][7][7];
    private static boolean kittenFound = false;

    public static void main(String[] args) {
        // Color The Different Wives Sacks
        for (int wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (int sackColorIndex = 0; sackColorIndex < 7; sackColorIndex++) {
                int sackIndex = sackColorIndex;
                MAN_ON_THE_ROAD[wifeIndex][sackIndex][0][0] = SACK_COLORS[sackColorIndex];
            }
        }

        // Setup The Rest Of The Search So We Can Find Hershey
        MAN_ON_THE_ROAD[0][2][1][5] = "Hershey";
        MAN_ON_THE_ROAD[0][0][0][0] = "Darlene";

        wifeLoop:
        for (int wifeIndex = 0; wifeIndex < 7 && !kittenFound; wifeIndex++) {
            sackLoop:
            for (int sackIndex = 0; sackIndex < 7 && !kittenFound; sackIndex++) {
                catLoop:
                for (int catIndex = 0; catIndex < 7 && !kittenFound; catIndex++) {
                    int kittenIndex = 0;
                    if (MAN_ON_THE_ROAD[wifeIndex][sackIndex][catIndex][kittenIndex] != null &&
                        MAN_ON_THE_ROAD[wifeIndex][sackIndex][catIndex][kittenIndex].equals("Hershey")) {
                        kittenFound = true;
                        break wifeLoop;
                    }
                }
            }
        }

        if (kittenFound) {
            System.out.println("Hershey found in the " + 
                MAN_ON_THE_ROAD[wifeIndex][sackIndex][0][0] + 
                " Sack, Being carried by " + 
                MAN_ON_THE_ROAD[0][0][0][0]);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}