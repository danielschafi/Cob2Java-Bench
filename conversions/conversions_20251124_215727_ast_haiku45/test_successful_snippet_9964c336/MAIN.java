import java.util.Scanner;

public class MAIN {
    private static int intN;
    private static String strTs;
    private static int intT;
    private static String strS;
    private static int intH = 0;
    private static int intX = 0;
    private static int intY;
    private static int intP = 24;
    private static int intI;
    private static int intZ;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        intN = scanner.nextInt();
        scanner.nextLine();
        
        for (intI = 1; intI <= intN; intI++) {
            strTs = scanner.nextLine();
            
            String[] parts = strTs.split(" ");
            intT = Integer.parseInt(parts[0]);
            strS = parts.length > 1 ? parts[1] : "";
            
            if (intX > 0) {
                intY = intT;
                intY = intY - intH;
                
                if (intY >= intX) {
                    intP = intP + intX;
                    intX = 0;
                } else {
                    intP = intP + intY;
                    intX = intX - intY;
                }
            }
            
            intH = intT;
            
            if (strS.equals("out")) {
                intX = intX + 3;
            } else {
                intX = intX + 5;
            }
        }
        
        if (intX > 0) {
            intY = 24;
            intY = intY - intH;
            
            if (intY >= intX) {
                intP = intP + intX;
            } else {
                intP = intP + intY;
            }
        }
        
        intZ = intP;
        System.out.println(String.valueOf(intZ).trim());
        
        scanner.close();
    }
}