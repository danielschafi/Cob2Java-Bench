public class UnstringSample {
    private static String[] tWert = new String[11];
    private static String zWert1;
    private static String zWert2;
    private static String zWert3;
    private static String zWert4;
    private static String zBtrnrE;
    private static String zBtrnrA;
    private static String zEmp1;
    private static String zEmp2;
    private static String zEmp3;
    private static String zAbs1;
    private static String zAbs2;
    private static String zAbs3;
    private static int x1;
    private static int x2;
    private static String zWert;
    
    public static void main(String[] args) {
        b70100();
    }
    
    public static void b70100() {
        x2 = 0;
        String[] tempArray = zWert.split("#");
        for (int i = 0; i < Math.min(tempArray.length, 11); i++) {
            tWert[i] = tempArray[i];
        }
        x2 = tempArray.length;
        
        for (x1 = 1; x1 <= x2; x1++) {
            zWert = "";
            String[] tempArray2 = tWert[x1 - 1].split("[= ]+");
            if (tempArray2.length >= 2) {
                zWert1 = tempArray2[0];
                zWert2 = tempArray2[1];
                
                switch (zWert1) {
                    case "EMP-1":
                        zEmp1 = zWert2;
                        break;
                    case "EMP-2":
                        zEmp2 = zWert2;
                        break;
                    case "EMP-3":
                        zEmp3 = zWert2;
                        break;
                    case "ABS-1":
                        zAbs1 = zWert2;
                        break;
                    case "ABS-2":
                        zAbs2 = zWert2;
                        break;
                    case "ABS-3":
                        zAbs3 = zWert2;
                        break;
                    case "BTRNR-A":
                        zBtrnrA = zWert2;
                        break;
                    case "BTRNR-E":
                        zBtrnrE = zWert2;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}