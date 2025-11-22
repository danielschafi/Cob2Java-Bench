import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intN = scanner.nextInt();
        int intH = 0;
        int intX = 0;
        int intP = 24;
        int intZ;

        for (int intI = 1; intI <= intN; intI++) {
            String strTs = scanner.next();
            String[] parts = strTs.split(" ");
            int intT = Integer.parseInt(parts[0]);
            String strS = parts[1];

            if (intX > 0) {
                int intY = intT - intH;
                if (intY >= intX) {
                    intP += intX;
                    intX = 0;
                } else {
                    intP += intY;
                    intX -= intY;
                }
            }
            intH = intT;
            if (strS.equals("out")) {
                intX += 3;
            } else {
                intX += 5;
            }
        }

        if (intX > 0) {
            int intY = 24 - intH;
            if (intY >= intX) {
                intP += intX;
            } else {
                intP += intY;
            }
        }

        intZ = intP;
        System.out.println(String.valueOf(intZ).trim());
    }
}