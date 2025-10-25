import java.util.Arrays;

public class Chapt12x {
    public static void main(String[] args) {
        String[][] sackColors = {
            {"Blue", "Red", "Yellow", "Green", "Purple", "Black", "White"}
        };

        String[][][][] theManOnTheRoad = new String[7][7][7][7];
        boolean kittenFound = false;

        for (int wifeIndex = 0; wifeIndex < 7; wifeIndex++) {
            for (int sackColorIndex = 0; sackColorIndex < 7; sackColorIndex++) {
                int sackIndex = sackColorIndex;
                theManOnTheRoad[wifeIndex][sackIndex][0][0] = sackColors[0][sackColorIndex];
            }
        }

        theManOnTheRoad[0][2][1][5] = "Hershey";
        theManOnTheRoad[0][0][0][0] = "Darlene";

        for (int wifeIndex = 0; wifeIndex < 7 && !kittenFound; wifeIndex++) {
            for (int sackIndex = 0; sackIndex < 7 && !kittenFound; sackIndex++) {
                for (int catIndex = 0; catIndex < 7 && !kittenFound; catIndex++) {
                    int kittenIndex = 0;
                    if ("Hershey".equals(theManOnTheRoad[wifeIndex][sackIndex][catIndex][kittenIndex])) {
                        kittenFound = true;
                    }
                }
            }
        }

        if (kittenFound) {
            System.out.println("Hershey found in the " +
                theManOnTheRoad[wifeIndex - 1][sackIndex - 1][0][0] +
                " Sack, Being carried by " +
                theManOnTheRoad[wifeIndex - 1][0][0][0]);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}