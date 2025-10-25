import java.util.Arrays;

public class UnstringSample {
    private int cUeb = 0;
    private int cD852a = 0;
    private int x1 = 0;
    private int x2 = 0;
    private int x4 = 0;
    private short zLaen = 0;
    private String zDatum = "        ";
    private String zJahr = "    ";
    private String zMonat = "  ";
    private String zTag = "  ";
    private String zZeit = "      ";
    private String zWert1 = "                                                                      ";
    private String zWert2 = "                                                                      ";
    private String zWert3 = "                                                                      ";
    private String zWert4 = "                                                                      ";
    private String zBtrnrE = "        ";
    private String zBtrnrA = "        ";
    private String zEmp1 = "                                   ";
    private String zEmp2 = "                                   ";
    private String zEmp3 = "                                   ";
    private String zAbs1 = "                                   ";
    private String zAbs2 = "                                   ";
    private String zAbs3 = "                                   ";
    private String zBand = "     ";
    private String zBandNr = "     ";
    private String zUeb = "EL";
    private String zUeb1 = "EL";
    private int zUeb2 = 0;
    private String[] tWert = new String[11];
    private String zWert = "                                                                      ";

    public static void main(String[] args) {
        UnstringSample program = new UnstringSample();
        program.b70100();
    }

    private void b70100() {
        x2 = 0;
        String[] parts = zWert.split("#");
        for (int i = 0; i < Math.min(parts.length, 11); i++) {
            tWert[i] = parts[i];
        }
        x2 = parts.length;

        for (x1 = 1; x1 <= x2; x1++) {
            zWert1 = "                                                                      ";
            zWert2 = "                                                                      ";
            String[] subParts = tWert[x1 - 1].split("[= ]+");
            if (subParts.length > 0) {
                zWert1 = subParts[0];
            }
            if (subParts.length > 1) {
                zWert2 = subParts[1];
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