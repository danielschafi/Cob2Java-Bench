```java
import java.util.ArrayList;
import java.util.List;

public class UnstringSample {
    private int cUeb = 0;
    private int cD852a = 0;
    
    private String[] tWert = new String[11];
    
    private int x1 = 0;
    private int x2 = 0;
    private int x4 = 0;
    
    private int zLaen = 0;
    private String zJahr = "";
    private String zMonat = "";
    private String zTag = "";
    private String zZeit = "";
    private String zWert1 = "";
    private String zWert2 = "";
    private String zWert3 = "";
    private String zWert4 = "";
    private String zBtrnrE = "";
    private String zBtrnrA = "";
    private String zEmp1 = "";
    private String zEmp2 = "";
    private String zEmp3 = "";
    private String zAbs1 = "";
    private String zAbs2 = "";
    private String zAbs3 = "";
    private int zBandNr = 0;
    private String zUeb1 = "EL";
    private int zUeb2 = 0;
    
    private String zWert = "";
    
    public UnstringSample() {
        for (int i = 0; i < 11; i++) {
            tWert[i] = "";
        }
    }
    
    public static void main(String[] args) {
        UnstringSample program = new UnstringSample();
        program.b70100();
    }
    
    private void b70100() {
        x2 = 0;
        
        unstring(zWert, "#", tWert);
        
        for (x1 = 1; x1 <= x2; x1++) {
            zWert = "";
            
            String[] parts = unstring(tWert[x1 - 1], "=", " ");
            
            if (parts.length >= 1) {
                zWert1 = parts[0].trim();
            }
            if (parts.length >= 2) {
                zWert2 = parts[1].trim();
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
    
    private void unstring(String source, String delimiter, String[] targets) {
        String[] parts = source.split("\\" + delimiter);
        x2 = Math.min(parts.length, targets.length);
        
        for (int i = 0; i < x2; i++) {
            targets[i] = parts[i];
        }
    }
    
    private String[] unstring(String source, String delimiter1, String delimiter2) {
        List<String> result = new ArrayList<>();
        
        String[] parts1 = source.split("\\" + delimiter1);
        
        for (String part : parts1) {
            String[] parts2 = part.split("\\" + delimiter2);
            for (String p : parts2) {
                if (!p.isEmpty()) {
                    result.add(p);
                }
            }
        }
        
        return result.toArray(new String[0]);
    }
}