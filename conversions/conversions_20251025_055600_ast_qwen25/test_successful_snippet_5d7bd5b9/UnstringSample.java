import java.util.Arrays;

public class UnstringSample {
    private int x2;
    private String[] tWert = new String[11];
    private String zWert1;
    private String zWert2;
    private String zEmp1 = "                               ";
    private String zEmp2 = "                               ";
    private String zEmp3 = "                               ";
    private String zAbs1 = "                               ";
    private String zAbs2 = "                               ";
    private String zAbs3 = "                               ";
    private String zBtrnrE = "        ";
    private String zBtrnrA = "        ";

    public static void main(String[] args) {
        UnstringSample program = new UnstringSample();
        program.b70100();
    }

    private void b70100() {
        x2 = 0;
        String zWert = "EMP-1=John Doe#EMP-2=Jane Smith#EMP-3=Jim Brown#ABS-1=12345#ABS-2=67890#ABS-3=54321#BTRNR-A=A1234567#BTRNR-E=E7654321";
        String[] parts = zWert.split("#");
        x2 = parts.length;
        System.arraycopy(parts, 0, tWert, 0, x2);

        for (int x1 = 0; x1 < x2; x1++) {
            Arrays.fill(new char[zWert1.length()], ' ');
            Arrays.fill(new char[zWert2.length()], ' ');
            String[] subParts = tWert[x1].split("=");
            if (subParts.length > 0) zWert1 = subParts[0];
            if (subParts.length > 1) zWert2 = subParts[1];

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