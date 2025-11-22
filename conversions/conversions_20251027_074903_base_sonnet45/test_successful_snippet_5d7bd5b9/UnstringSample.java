import java.util.Arrays;

public class UnstringSample {

    private static class CZaehler {
        int cUeb = 0;
        int cD852a = 0;
    }

    private static class TTabellen {
        String[] tWert = new String[11];
        
        TTabellen() {
            Arrays.fill(tWert, "");
        }
    }

    private static class XSubskripte {
        int x1 = 0;
        int x2 = 0;
        int x4 = 0;
    }

    private static class ZZwischenbereiche {
        int zLaen = 0;
        
        static class ZDatum {
            String zJahr = "";
            String zMonat = "";
            String zTag = "";
            String zZeit = "";
            String filler = "";
        }
        ZDatum zDatum = new ZDatum();
        
        static class ZWert {
            String zWert1 = "";
            String zWert2 = "";
            String zWert3 = "";
            String zWert4 = "";
        }
        ZWert zWert = new ZWert();
        
        String zBtrnrE = "";
        String zBtrnrA = "";
        String zEmp1 = "";
        String zEmp2 = "";
        String zEmp3 = "";
        String zAbs1 = "";
        String zAbs2 = "";
        String zAbs3 = "";
        
        static class ZBand {
            int zBandNr = 0;
            String filler = "";
        }
        ZBand zBand = new ZBand();
        
        static class ZUeb {
            String zUeb1 = "EL";
            int zUeb2 = 0;
        }
        ZUeb zUeb = new ZUeb();
    }

    private static CZaehler cZaehler = new CZaehler();
    private static TTabellen tTabellen = new TTabellen();
    private static XSubskripte xSubskripte = new XSubskripte();
    private static ZZwischenbereiche zZwischenbereiche = new ZZwischenbereiche();

    public static void main(String[] args) {
        b70100();
    }

    private static void b70100() {
        xSubskripte.x2 = 0;
        
        String zWertCombined = zZwischenbereiche.zWert.zWert1 + 
                               zZwischenbereiche.zWert.zWert2 + 
                               zZwischenbereiche.zWert.zWert3 + 
                               zZwischenbereiche.zWert.zWert4;
        
        String[] parts = zWertCombined.split("#+");
        int count = 0;
        for (int i = 0; i < parts.length && i < 11; i++) {
            if (!parts[i].isEmpty() || (i < parts.length - 1)) {
                tTabellen.tWert[i] = padRight(parts[i], 72);
                count++;
            }
        }
        xSubskripte.x2 = count;
        
        for (xSubskripte.x1 = 0; xSubskripte.x1 < xSubskripte.x2; xSubskripte.x1++) {
            zZwischenbereiche.zWert.zWert1 = "";
            zZwischenbereiche.zWert.zWert2 = "";
            zZwischenbereiche.zWert.zWert3 = "";
            zZwischenbereiche.zWert.zWert4 = "";
            
            String currentValue = tTabellen.tWert[xSubskripte.x1];
            
            String[] tokens = currentValue.split("=+|  +", 2);
            if (tokens.length > 0) {
                zZwischenbereiche.zWert.zWert1 = padRight(tokens[0].trim(), 72);
            }
            if (tokens.length > 1) {
                zZwischenbereiche.zWert.zWert2 = padRight(tokens[1].trim(), 72);
            }
            
            String key = zZwischenbereiche.zWert.zWert1.trim();
            String value = zZwischenbereiche.zWert.zWert2.trim();
            
            switch (key) {
                case "EMP-1":
                    zZwischenbereiche.zEmp1 = padRight(value, 35);
                    break;
                case "EMP-2":
                    zZwischenbereiche.zEmp2 = padRight(value, 35);
                    break;
                case "EMP-3":
                    zZwischenbereiche.zEmp3 = padRight(value, 35);
                    break;
                case "ABS-1":
                    zZwischenbereiche.zAbs1 = padRight(value, 35);
                    break;
                case "ABS-2":
                    zZwischenbereiche.zAbs2 = padRight(value, 35);
                    break;
                case "ABS-3":
                    zZwischenbereiche.zAbs3 = padRight(value, 35);
                    break;
                case "BTRNR-A":
                    zZwischenbereiche.zBtrnrA = padRight(value, 8);
                    break;
                case "BTRNR-E":
                    zZwischenbereiche.zBtrnrE = padRight(value, 8);
                    break;
                default:
                    break;
            }
        }
    }

    private static String padRight(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}