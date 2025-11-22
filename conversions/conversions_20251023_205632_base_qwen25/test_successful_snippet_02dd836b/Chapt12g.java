import java.util.Arrays;

public class Chapt12g {
    public static void main(String[] args) {
        String[][][] wife = new String[7][7][7];
        String[][] sackColor = new String[7][7];
        boolean kittenFound = false;

        wife[0][2][1] = "Hershey";
        wife[0][0][0] = "Darlene";
        sackColor[0][2] = "Yellow";

        for (int wifeIndex = 0; wifeIndex < 7 && !kittenFound; wifeIndex++) {
            for (int sackIndex = 0; sackIndex < 7 && !kittenFound; sackIndex++) {
                for (int catIndex = 0; catIndex < 7 && !kittenFound; catIndex++) {
                    for (int kittenIndex = 0; kittenIndex < 7; kittenIndex++) {
                        if ("Hershey".equals(wife[wifeIndex][sackIndex][catIndex])) {
                            kittenFound = true;
                            break;
                        }
                    }
                }
            }
        }

        if (kittenFound) {
            System.out.println("Hershey found in the " + sackColor[wifeIndex][sackIndex] + " Sack, Being carried by " + wife[wifeIndex][0][0]);
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}