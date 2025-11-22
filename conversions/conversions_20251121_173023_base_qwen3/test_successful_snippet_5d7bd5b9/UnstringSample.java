public class UnstringSample {
    private static int cZaehler;
    private static int cD852A;
    private static String[] tWert = new String[11];
    private static int x1;
    private static int x2;
    private static int x4;
    private static int zLaen;
    private static String zJahr;
    private static String zMonat;
    private static String zTag;
    private static String zZeit;
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
    private static int zBandNr;
    private static String zUeb1;
    private static int zUeb2;

    public static void main(String[] args) {
        b70100();
    }

    public static void b70100() {
        x2 = 0;
        String zWert = "EMP-1=Max Mustermann  EMP-2=Erika Musterfrau  EMP-3=Hans Meier  ABS-1=100,50  ABS-2=200,75  ABS-3=300,25  BTRNR-A=12345678  BTRNR-E=87654321";
        String[] tempArray = zWert.split("#");
        for (int i = 0; i < Math.min(tempArray.length, 11); i++) {
            tWert[i] = tempArray[i];
        }
        x2 = tempArray.length;

        for (x1 = 1; x1 <= x2; x1++) {
            zWert1 = "";
            zWert2 = "";
            String tWertX1 = tWert[x1 - 1];
            String[] parts = tWertX1.split("=", 2);
            if (parts.length >= 2) {
                zWert1 = parts[0].trim();
                zWert2 = parts[1].trim();
            } else {
                zWert1 = tWertX1.trim();
            }

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