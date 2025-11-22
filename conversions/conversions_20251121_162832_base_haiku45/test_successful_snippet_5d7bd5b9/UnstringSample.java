public class UnstringSample {
    
    private static class CZaehler {
        int cUeb = 0;
        int cD852a = 0;
    }
    
    private static class TTabellen {
        String[] tWert = new String[11];
        
        TTabellen() {
            for (int i = 0; i < 11; i++) {
                tWert[i] = "";
            }
        }
    }
    
    private static class XSubskripte {
        int x1 = 0;
        int x2 = 0;
        int x4 = 0;
    }
    
    private static class ZZwischenbereiche {
        int zLaen = 0;
        String zJahr = "";
        String zMonat = "";
        String zTag = "";
        String zZeit = "";
        String zWert1 = "";
        String zWert2 = "";
        String zWert3 = "";
        String zWert4 = "";
        String zBtrnrE = "";
        String zBtrnrA = "";
        String zEmp1 = "";
        String zEmp2 = "";
        String zEmp3 = "";
        String zAbs1 = "";
        String zAbs2 = "";
        String zAbs3 = "";
        int zBandNr = 0;
        String zUeb1 = "EL";
        int zUeb2 = 0;
    }
    
    private static CZaehler cZaehler = new CZaehler();
    private static TTabellen tTabellen = new TTabellen();
    private static XSubskripte xSubskripte = new XSubskripte();
    private static ZZwischenbereiche zZwischenbereiche = new ZZwischenbereiche();
    
    public static void main(String[] args) {
        b70100();
        System.exit(0);
    }
    
    private static void b70100() {
        String zWert = "";
        
        xSubskripte.x2 = 0;
        
        String[] parts = zWert.split("#", -1);
        for (int i = 0; i < Math.min(parts.length, 11); i++) {
            tTabellen.tWert[i] = parts[i];
        }
        xSubskripte.x2 = Math.min(parts.length, 11);
        
        for (xSubskripte.x1 = 0; xSubskripte.x1 < xSubskripte.x2; xSubskripte.x1++) {
            zZwischenbereiche.zWert1 = "";
            zZwischenbereiche.zWert2 = "";
            
            String tWertStr = tTabellen.tWert[xSubskripte.x1];
            String[] unstringParts = unstring(tWertStr, "=", "  ");
            
            if (unstringParts.length > 0) {
                zZwischenbereiche.zWert1 = unstringParts[0].trim();
            }
            if (unstringParts.length > 1) {
                zZwischenbereiche.zWert2 = unstringParts[1].trim();
            }
            
            switch (zZwischenbereiche.zWert1) {
                case "EMP-1":
                    zZwischenbereiche.zEmp1 = zZwischenbereiche.zWert2;
                    break;
                case "EMP-2":
                    zZwischenbereiche.zEmp2 = zZwischenbereiche.zWert2;
                    break;
                case "EMP-3":
                    zZwischenbereiche.zEmp3 = zZwischenbereiche.zWert2;
                    break;
                case "ABS-1":
                    zZwischenbereiche.zAbs1 = zZwischenbereiche.zWert2;
                    break;
                case "ABS-2":
                    zZwischenbereiche.zAbs2 = zZwischenbereiche.zWert2;
                    break;
                case "ABS-3":
                    zZwischenbereiche.zAbs3 = zZwischenbereiche.zWert2;
                    break;
                case "BTRNR-A":
                    zZwischenbereiche.zBtrnrA = zZwischenbereiche.zWert2;
                    break;
                case "BTRNR-E":
                    zZwischenbereiche.zBtrnrE = zZwischenbereiche.zWert2;
                    break;
                default:
                    break;
            }
        }
    }
    
    private static String[] unstring(String input, String... delimiters) {
        String pattern = "";
        for (int i = 0; i < delimiters.length; i++) {
            if (i > 0) pattern += "|";
            pattern += java.util.regex.Pattern.quote(delimiters[i]);
        }
        return input.split(pattern, -1);
    }
}