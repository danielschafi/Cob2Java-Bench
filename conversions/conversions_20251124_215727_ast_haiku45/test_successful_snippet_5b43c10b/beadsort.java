```java
import java.util.Random;

public class beadsort {
    private static String[] row = new String[9];
    private static int r;
    private static int r1;
    private static int r2;
    private static int pole;
    private static int aLim = 9;
    private static int a;
    private static int[] array = new int[9];
    private static String NL = "\n";

    public static void main(String[] args) {
        startBeadsort();
    }

    private static void startBeadsort() {
        Random random = new Random();
        a = random.nextInt(Integer.MAX_VALUE);
        
        for (a = 1; a <= aLim; a++) {
            array[a - 1] = (int)(random.nextDouble() * 10);
        }

        displayArray();
        System.out.print(" initial array");

        for (r = 1; r <= aLim; r++) {
            row[r - 1] = ".........";
            for (pole = 1; pole <= array[r - 1]; pole++) {
                row[r - 1] = row[r - 1].substring(0, pole - 1) + "o" + row[r - 1].substring(pole);
            }
        }
        System.out.print(NL + "initial beads");
        displayBeads();

        for (pole = 1; pole <= aLim; pole++) {
            r2 = aLim;
            findOpening();
            r1 = r2 - 1;
            findBead();
            while (r1 != 0) {
                row[r1 - 1] = row[r1 - 1].substring(0, pole - 1) + "." + row[r1 - 1].substring(pole);
                row[r2 - 1] = row[r2 - 1].substring(0, pole - 1) + "o" + row[r2 - 1].substring(pole);

                r2 = r2 - 1;
                findOpening();
                r1 = r2 - 1;
                findBead();
            }
        }
        System.out.print(NL + "dropped beads");
        displayBeads();

        for (r = 1; r <= aLim; r++) {
            array[r - 1] = 0;
            String rowStr = row[r - 1];
            int dotIndex = rowStr.indexOf('.');
            if (dotIndex == -1) {
                dotIndex = rowStr.length();
            }
            for (int i = 0; i < dotIndex; i++) {
                if (rowStr.charAt(i) == 'o') {
                    array[r - 1]++;
                }
            }
        }

        displayArray();
        System.out.print(" sorted array");

        System.exit(0);
    }

    private static void findOpening() {
        while (r2 > 1 && row[r2 - 1].charAt(pole - 1) != '.') {
            r2 = r2 - 1;
        }
    }

    private static void findBead() {
        while (r1 > 0 && row[r1 - 1].charAt(pole - 1) != 'o') {
            r1 = r1 - 1;
        }
    }

    private static void displayArray() {
        System.out.print(" ");
        for (a = 1; a <= aLim; a++) {
            System.out.print(" " + array[a - 1]);
        }
    }

    private static void displayBeads() {
        for (r = 1; r <= aLim; r++) {
            System.out.println(row[r - 1]);
        }
    }
}