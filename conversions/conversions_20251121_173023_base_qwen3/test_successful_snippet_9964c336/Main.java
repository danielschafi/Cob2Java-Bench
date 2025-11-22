import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int intN = scanner.nextInt();
        scanner.nextLine();
        
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
            
            int spaceIndex = strTs.indexOf(" ");
            intT = Integer.parseInt(strTs.substring(0, spaceIndex));
            strS = strTs.substring(spaceIndex + 1);
            
            if (intX > 0) {
                intY = intT - intH;
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
            intY = 24 - intH;
            if (intY >= intX) {
                intP += intX;
            } else {
                intP += intY;
            }
        }
        
        intZ = intP;
        System.out.println(intZ);
    }
}