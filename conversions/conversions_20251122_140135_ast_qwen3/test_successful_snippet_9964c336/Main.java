import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int intN = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        int intT = 0;
        int intS = 0;
        int intH = 0;
        int intX = 0;
        int intY = 0;
        int intP = 24;
        int intI = 0;
        int intZ = 0;
        
        for (intI = 1; intI <= intN; intI++) {
            String strTS = scanner.nextLine();
            
            // Parse strTS to get intT and strS
            String[] parts = strTS.split(" ", 2);
            intT = Integer.parseInt(parts[0]);
            String strS = parts.length > 1 ? parts[1] : "";
            
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