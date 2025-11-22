import java.util.Arrays;

public class Chapt12g {
    public static void main(String[] args) {
        String[][][][] kittenName = new String[7][7][7][7];
        String[][] wifeName = new String[7][20];
        String[][] sackColor = new String[7][10];
        boolean kittenFound = false;

        kittenName[0][2][1][5] = "Hershey";
        Arrays.fill(wifeName[0], " ");
        Arrays.fill(sackColor[0], " ");
        wifeName[0] = "Darlene".toCharArray();
        sackColor[0] = "Yellow".toCharArray();

        for (int wifeIndex = 0; wifeIndex < 7 && !kittenFound; wifeIndex++) {
            for (int sackIndex = 0; sackIndex < 7 && !kittenFound; sackIndex++) {
                for (int catIndex = 0; catIndex < 7 && !kittenFound; catIndex++) {
                    for (int kittenIndex = 0; kittenIndex < 7; kittenIndex++) {
                        if ("Hershey".equals(kittenName[wifeIndex][sackIndex][catIndex][kittenIndex])) {
                            kittenFound = true;
                            break;
                        }
                    }
                }
            }
        }

        if (kittenFound) {
            System.out.println("Hershey found in the " + new String(sackColor[wifeIndex]) + " Sack, Being carried by " + new String(wifeName[wifeIndex]));
        } else {
            System.out.println("Hershey Escaped");
        }
    }
}