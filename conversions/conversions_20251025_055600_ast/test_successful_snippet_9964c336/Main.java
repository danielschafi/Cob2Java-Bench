import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int intN = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        int intT = 0;
        String strS = "";
        int intH = 0;
        int intX = 0;
        int intY = 0;
        int intP = 24;
        int intI = 0;
        int intZ = 0;

        for (intI = 1; intI <= intN; intI++) {
            String strTs = scanner.nextLine();
            String[] parts = strTs.split(" ");
            intT = Integer.parseInt(parts[0]);
            strS = parts[1];

            if (intX > 0) {
                intY = intT;
                intY -= intH;
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
            intY = 24;
            intY -= intH;
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